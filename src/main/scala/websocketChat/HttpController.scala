//package websocketChat
//
//import DB.RoomRepository
//import akka.actor.ActorSystem
//import akka.http.scaladsl.server.Route
//import akka.http.scaladsl.server.Directives._
//import akka.stream.Materializer
//import de.heikoseeberger.akkahttpjson4s.Json4sSupport
//import org.json4s.jackson.Serialization
//import org.json4s.{DefaultFormats, jackson}
//import websocketChat.chat.ChatRooms
//
//import scala.concurrent.Await
//import scala.concurrent.duration.Duration
//
//class HttpController(implicit actorSystem: ActorSystem, materializer: Materializer) extends Json4sSupport {
//
//  implicit val format: DefaultFormats.type = DefaultFormats
//  implicit val serialization: Serialization.type = jackson.Serialization
//
//  val messagesRepository = new MessagesRepository
//
//  val routes: Route = get {
//    pathEndOrSingleSlash {
//      getFromResource("webclient/index.html")
//    } ~  {
//      getFromResourceDirectory("webclient")
//    }
//  } ~ pathPrefix("ws-chat" / IntNumber) { chatId =>
//    parameter('name) { userName =>
//      handleWebSocketMessages(ChatRooms.findOrCreate(chatId).websocketFlow(userName))
//    }
//  } ~ pathPrefix("history" / IntNumber) { roomId =>
//    val messages = Await.result(messagesRepository.getMessagesById(roomId), Duration.Inf)
//    complete(messages.map(_.data))
//  }
//}