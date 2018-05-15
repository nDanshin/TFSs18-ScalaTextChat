package websocketChat.chat

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.{FlowShape, OverflowStrategy}
import akka.stream.scaladsl._

class ChatRoom(roomId: Int, actorSystem: ActorSystem) {

  private[this] val chatRoomActor = actorSystem.actorOf(Props(classOf[ChatRoomActor], roomId))

  val chatActorSource = Source.actorRef[ChatMessage](bufferSize = 5, OverflowStrategy.fail)

  def websocketFlow(user: String): Flow[Message, Message, _] = {
    Flow.fromGraph(GraphDSL.create(chatActorSource){ implicit builder => chatSource =>
      import GraphDSL.Implicits._

      val materialization = builder.materializedValue.map(actor => UserJoined(user, actor))
      val merge = builder.add(Merge[ChatEvent](2))

      val fromWebsocket = builder.add(Flow[Message].collect {
        case TextMessage.Strict(txt) => ChatMessage(user, txt)
      })

      val backToWebsocket = builder.add(Flow[ChatMessage].map {
        case ChatMessage(sender, text) => TextMessage(s"[$sender]: $text")
      })

      val chatActorSink = Sink.actorRef[ChatEvent](chatRoomActor, UserLeft(user))

      materialization ~> merge.in(1)
      fromWebsocket ~> merge.in(0)


      merge ~> chatActorSink
      chatSource ~> backToWebsocket

      FlowShape(fromWebsocket.in, backToWebsocket.out)
    })
  }

  def sendMessage(message: ChatMessage): Unit = chatRoomActor ! message

}

object ChatRoom {
  def apply(roomId: Int)(implicit actorSystem: ActorSystem) = new ChatRoom(roomId, actorSystem)
}
