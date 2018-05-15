import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.H2Profile.api._
import datamodel.latest.schema.tables._

object Application {
  def main(args: Array[String]) {
    try {
      val f: Seq[MessagesRow] = Await.result(MyDatabase.db.run(Messages.result), Duration.Inf)
      f.foreach(println)
      val g: Seq[MessagesRow] = Await.result(MyDatabase.db.run(Messages.result), Duration.Inf)
      g.filter(_.id == 1).foreach(println)
    } finally {
      MyDatabase.db.close()
    }
  }
}
