package DB

import datamodel.latest.schema.tables
import scala.concurrent.Future

class MessagesRepository extends Db with tables {
  override val profile = config.profile

  import config.profile.api._
  import scala.concurrent.ExecutionContext.Implicits.global

  def insertMessage(id: Int, data: String): Future[Int] = db.run(Messages += MessagesRow(id, data))

  def getMessagesById(id: Int): Future[Seq[MessagesRow]] = {
    db.run(Messages.filter(_.id === id).result)
  }
}
