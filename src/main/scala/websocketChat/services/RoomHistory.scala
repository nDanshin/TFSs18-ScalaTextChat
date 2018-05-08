package websocketChat.services

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

object RoomHistory {

  val map = Map(123 -> "123s", 5 -> "5s")

  def route: Route = pathPrefix("history" / IntNumber) { roomId =>
    complete(map.getOrElse(roomId, "").toString)
  }
}
