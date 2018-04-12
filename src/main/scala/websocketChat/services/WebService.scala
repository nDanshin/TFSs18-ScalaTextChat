package websocketChat.services

import akka.http.scaladsl.server.Route

trait WebService {
  def route: Route
}
