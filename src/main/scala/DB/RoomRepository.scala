
package DB


import Utils.{DBComponent, H2DBImpl, MySQLDBImpl}

import scala.concurrent.Future


trait RoomRepository extends RoomTable { this: DBComponent =>

  import driver.api._


  def createRoom(room: Room): Future[Int] = db.run { roomTableAutoInc += room }

  def updateRoom(room: Room): Future[Int] = db.run { roomTableQuery.filter(_.id === room.id.get).update(room) }

  def getRoomById(id: Int): Future[Option[Room]] = db.run { roomTableQuery.filter(_.id === id).result.headOption }

  // def getAllBanks(): Future[List[Room]] = db.run { roomTableQuery.to[List].result }

  // def deleteBank(id: Int): Future[Int] = db.run { roomTableQuery.filter(_.id === id).delete }

  def ddl=db.run {roomTableQuery.schema.create}

}

trait RoomTable { this: DBComponent =>

  import driver.api._

  class RoomTable(tag: Tag) extends Table[Room](tag, "ROOM") {
    val id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    val text = column[String]("NAME")
    def * = (text, id.?) <> (Room.tupled, Room.unapply)

  }

  protected val roomTableQuery = TableQuery[RoomTable]

  protected def roomTableAutoInc = roomTableQuery returning roomTableQuery.map(_.id)

}


class RoomRepositoryImpl extends RoomRepository with H2DBImpl

case class Room(name: String, id: Option[Int] = None)