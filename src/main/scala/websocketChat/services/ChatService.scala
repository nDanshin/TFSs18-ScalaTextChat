package websocketChat.services

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import websocketChat.chat.ChatRooms
//import javax.inject.{Inject, Named}

object ChatService {

  def route(implicit actorSystem: ActorSystem, materializer: Materializer): Route = pathPrefix("ws-chat" / IntNumber) { chatId =>
    parameter('name) { userName =>
      handleWebSocketMessages(ChatRooms.findOrCreate(chatId).websocketFlow(userName))
    }
  }

}
