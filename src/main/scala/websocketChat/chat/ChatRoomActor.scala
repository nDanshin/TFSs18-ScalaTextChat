package websocketChat.chat

import DB._
import akka.actor.{Actor, ActorRef}

class ChatRoomActor(roomId: Int) extends Actor {

  var participants: Map[String, ActorRef] = Map.empty[String, ActorRef]

 private val repositoryImpl: RoomRepositoryImpl = new RoomRepositoryImpl


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

     //repositoryImpl.createRoom(Room(msg.toString))
    repositoryImpl.updateRoom(Room(msg.toString))
  }

  def broadcast(message: ChatMessage): Unit = participants.values.foreach(_ ! message)
}
