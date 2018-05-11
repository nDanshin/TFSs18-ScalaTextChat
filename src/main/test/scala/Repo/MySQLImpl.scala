package Repo

import Utils.DBComponent
import slick.jdbc.MySQLProfile



trait MySQLImpl extends DBComponent {

  val driver = MySQLProfile

  import driver.api._

  val db: Database = MySqlDB.connectionPool

}

private[Repo] object MySqlDB {

  import slick.driver.MySQLDriver.api._

  val connectionPool = Database.forConfig("mysql")

}