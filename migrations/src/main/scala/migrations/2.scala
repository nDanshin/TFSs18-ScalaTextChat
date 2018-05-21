import slick.jdbc.H2Profile.api._
import com.liyaos.forklift.slick.DBIOMigration
import datamodel.v1.schema.tables._

object M2 {
  MyMigrations.migrations = MyMigrations.migrations :+ DBIOMigration(2)(
    DBIO.seq(Messages ++= Seq(
      MessagesRow(1, "Welcome to the main chat"),
      MessagesRow(1, "Enjoy")
    )))
}
