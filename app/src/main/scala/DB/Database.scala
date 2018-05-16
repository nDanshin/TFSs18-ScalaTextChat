package DB

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

trait Db {
  lazy val config = DatabaseConfig.forConfig[JdbcProfile]("slick")
  lazy val db = config.db
}