package websocketChat

import DB.{Room, RoomRepositoryImpl}
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer

import scala.io.StdIn
import websocketChat.services.{ChatService, EchoService, MainService, RoomHistory}

object Server extends App {

  import Directives._

  implicit val actorSystem = ActorSystem("akka-system")
  implicit val flowMaterializer = ActorMaterializer()

  val interface = "localhost"
  val port = 8080

 // val route = Controller.route
  val route = MainService.route ~
    EchoService.route ~
    ChatService.route ~
    RoomHistory.route

  val binding = Http().bindAndHandle(route, interface, port)
  println(s"Server is now online at http://$interface:$port\nPress RETURN to stop...")
  StdIn.readLine()

  import actorSystem.dispatcher

  binding.flatMap(_.unbind()).onComplete(_ => actorSystem.terminate())
  println("Server is down...")
}
