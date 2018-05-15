
package DB


import Utils.{DBComponent, H2DBImpl, MySQLDBImpl}
import org.slf4j.LoggerFactory

import scala.concurrent.Future


trait RoomRepository extends RoomTable { this: DBComponent =>
  import driver.api._


 // def createRoom(room: Room): Future[Int] = db.run { roomTableAutoInc += room }

//  def updateRoom(room: Room): Future[Int] = db.run { roomTableQuery.filter(_.id === room.id.get).update(room) }
//def updateRoom(room: Room): Future[Int] = db.run {
//  sqlu"""
//       UPDATE room SET name = '#${room.name}'  where id='1'
//    """
//}
  def insert(room:Room): Future[Int]= db.run{
    sqlu"""
       INSERT INTO room2 (NAME,ID) VALUES ('#${room.name}',#${room.id})
    """
  }


 //def getRoomById(id: Int): Future[Option[Room]] = db.run { roomTableQuery.filter(_.id === id).result.headOption }
  def getRoomById(id: Int)= db.run {
    sql"""
        SELECT name FROM room2 WHERE id = #$id
      """.as[String].headOption
}

  // def getAllBanks(): Future[List[Room]] = db.run { roomTableQuery.to[List].result }

  // def deleteBank(id: Int): Future[Int] = db.run { roomTableQuery.filter(_.id === id).delete }

  //def ddl=db.run {roomTableQuery.schema.create}

}

trait RoomTable { this: DBComponent =>

  import driver.api._

  class RoomTable(tag: Tag) extends Table[Room](tag, "ROOM2") {
    val id = column[Int]("ID")
    val text = column[String]("NAME")
    def * = (text, id) <> (Room.tupled, Room.unapply)

  }

  protected val roomTableQuery = TableQuery[RoomTable]

  protected def roomTableAutoInc = roomTableQuery returning roomTableQuery.map(_.id)

}


class RoomRepositoryImpl extends RoomRepository with H2DBImpl

case class Room(name: String, id: Int )