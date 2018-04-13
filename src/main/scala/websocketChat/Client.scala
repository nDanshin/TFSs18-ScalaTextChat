package websocketChat

import akka.actor.ActorSystem

import scala.io.StdIn


object Client extends App {
  implicit val actorSystem = ActorSystem("akka-system")

  val name = StdIn.readLine("Input your name: ")
  println("Trying to connect to main chat...")

  val client: WSClient = WSClient(s"http://localhost:8080/ws-chat/123?name=$name", name)

  if (client.connectBlocking()) {

    var message = StdIn.readLine()

    while (message != "/exit") {
      client.sendMessage(message)
      message = StdIn.readLine()
    }
  }

}
