package websocketChat.services

import DB.RoomRepositoryImpl
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.jackson.Serialization
import org.json4s.{DefaultFormats, jackson}

object RoomHistory  {
  implicit val format: DefaultFormats.type = DefaultFormats
  implicit val serialization: Serialization.type = jackson.Serialization

    val roomRepositoryImpl = new RoomRepositoryImpl


    def route: Route = pathPrefix("history" / IntNumber) { roomId =>
      complete(roomRepositoryImpl.getRoomById(roomId))
    }
  }


