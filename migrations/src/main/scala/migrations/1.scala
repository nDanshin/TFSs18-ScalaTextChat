import slick.jdbc.H2Profile.api._
import com.liyaos.forklift.slick.SqlMigration

object M1 {
  MyMigrations.migrations = MyMigrations.migrations :+ SqlMigration(1)(List(
    sqlu"create table MESSAGES (ID integer not null, DATA varchar not null)"
  ))
}
