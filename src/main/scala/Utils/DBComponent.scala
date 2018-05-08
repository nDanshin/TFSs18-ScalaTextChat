package Utils

//import slick.driver.JdbcProfile
import slick.jdbc.JdbcProfile

trait DBComponent {
  val driver: JdbcProfile

  import driver.api._

  val db: Database
}