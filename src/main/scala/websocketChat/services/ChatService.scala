package websocketChat.services

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import websocketChat.chat.ChatRooms

object ChatService {

  def route(implicit actorSystem: ActorSystem, materializer: Materializer): Route = path("chat") {
    get {
      parameters('chatId.as[Int], 'userName.as[String]) { (chatId, userName) =>
        handleWebSocketMessages(ChatRooms.findOrCreate(chatId).websocketFlow(userName))
      }
    }
  }

}
