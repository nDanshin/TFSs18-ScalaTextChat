package websocketChat.services

import DB.MessagesRepository
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import scala.concurrent.Await
import scala.concurrent.duration.Duration
//import de.heikoseeberger.akkahttpjson4s.Json4sSupport
//import org.json4s.jackson.Serialization
//import org.json4s.{DefaultFormats, jackson}

object RoomHistory {
  //implicit val format: DefaultFormats.type = DefaultFormats
  //implicit val serialization: Serialization.type = jackson.Serialization

    val messagesRepository = new MessagesRepository

    def route: Route = pathPrefix("history" / IntNumber) { roomId =>
      val messages = Await.result(messagesRepository.getMessagesById(roomId), Duration.Inf)
      complete(messages.map(_.data).mkString(";"))
    }
  }


