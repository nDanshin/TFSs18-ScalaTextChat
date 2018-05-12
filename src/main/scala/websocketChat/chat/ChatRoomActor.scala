package websocketChat.chat

import DB._
import akka.actor.{Actor, ActorRef}

class ChatRoomActor(roomId: Int) extends Actor {

  var participants: Map[String, ActorRef] = Map.empty[String, ActorRef]
 // val roomRepositoryImpl = new RoomRepositoryImpl // создание  репозитория комнат

  override def receive: Receive = {
    case UserJoined(name, actorRef) =>
      participants += name -> actorRef
      broadcast(SystemMessage(s"User $name joined channel[$roomId]..."))
      println(s"User $name joined channel[$roomId]...")

    case UserLeft(name) =>
      println(s"User $name left channel[$roomId]...")
      broadcast(SystemMessage(s"User $name left channel[$roomId]..."))
      participants -= name

    case msg: ChatMessage =>
      broadcast(msg)
      // что нибудь типо такого, но мне кажется тут нужно не создавать комнату, а отправлять новые сообщения в уже созданную)
     // roomRepositoryImpl.getRoomById(1)
  }

  def broadcast(message: ChatMessage): Unit = participants.values.foreach(_ ! message)
}
