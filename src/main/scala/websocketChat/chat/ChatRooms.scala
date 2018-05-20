package websocketChat.chat

import akka.actor.ActorSystem

import scala.collection.concurrent.TrieMap

class ChatRooms {
  //var chatRooms: Map[Int, ChatRoom] = Map.empty[Int, ChatRoom]
  // var chatRooms=scala.collection.mutable.Map.empty[Int, ChatRoom]
  var chatRooms: TrieMap[Int, ChatRoom] = TrieMap.empty[Int, ChatRoom]

  def findOrCreate(number: Int)(implicit actorSystem: ActorSystem): ChatRoom =
    chatRooms.getOrElse(number, createNewChatRoom(number))

  private def createNewChatRoom(number: Int)(implicit actorSystem: ActorSystem): ChatRoom = {
    val chatroom = ChatRoom(number)
    chatRooms += number -> chatroom
    chatroom
  }
}
