package websocketChat.chat

import DB.MessagesRepository
import akka.actor.{Actor, ActorRef}

class ChatRoomActor(roomId: Int) extends Actor {

  var participants: Map[String, ActorRef] = Map.empty[String, ActorRef]

 private val messagesRepository = new MessagesRepository


  override def receive: Receive = {
    case UserJoined(name, actorRef) =>
      participants += name -> actorRef
      broadcast(SystemMessage(s"User $name joined channel[$roomId]..."))
      println(s"User $name joined channel[$roomId]...")

    case UserLeft(name) =>
      println(s"User $name left channel[$roomId]...")
      broadcast(SystemMessage(s"User $name left channel[$roomId]..."))
      participants -= name

    case msg: ChatMessage => {
      val text = s"[${msg.sender}]: ${msg.text}"
      messagesRepository.insertMessage(roomId, text)
      broadcast(msg)
    }


  }

  def broadcast(message: ChatMessage): Unit = participants.values.foreach(_ ! message)
}
