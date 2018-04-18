package websocketChat.services

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

object MainService {
  /*def route: Route = pathEndOrSingleSlash {
    complete("Welcome to websocket server")
  }*/
  //get & pathPrefix("admin") work

  def route: Route = get {
    pathEndOrSingleSlash {
      getFromResource("index.html")
    } ~  {
      getFromResourceDirectory(".")
    }
  }

}
