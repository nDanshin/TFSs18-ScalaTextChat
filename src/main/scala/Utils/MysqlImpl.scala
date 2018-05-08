package Utils



import slick.jdbc.MySQLProfile

trait MySQLDBImpl extends DBComponent {

  val driver = MySQLProfile

  import driver.api._

  val db: Database = MySqlDB.connectionPool

}

private[Utils] object MySqlDB {

  import slick.jdbc.MySQLProfile.api._

  val connectionPool = Database.forConfig("mysql")

}