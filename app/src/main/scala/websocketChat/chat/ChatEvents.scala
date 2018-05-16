package websocketChat.chat

import akka.actor.ActorRef


object SystemMessage {
  def apply(text: String) = ChatMessage("System", text)
}

sealed trait ChatEvent
case class UserJoined(name: String, userActor: ActorRef) extends ChatEvent
case class UserLeft(name: String) extends ChatEvent
case class ChatMessage(sender: String, text: String) extends ChatEvent
