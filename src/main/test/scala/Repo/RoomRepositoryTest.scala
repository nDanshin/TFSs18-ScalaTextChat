package scala.Repo

import DB.{Room, RoomRepository}
import Repo.{H2DBTest}

import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}


class RoomRepositoryTest extends FunSuite with RoomRepository with H2DBTest with ScalaFutures {

  implicit val defaultPatience = PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

//  test("Add new bank ") {
//    val response = createRoom(Room("Vasya"))
//    whenReady(response) { roomId =>
//      assert(roomId === 4)
//    }
//  }
//
//  test("Update  rus  ") {
//    val response = updateRoom(Room("rus", Some(1)))
//    whenReady(response) { res =>
//      assert(res === 1)
//    }
//  }

}