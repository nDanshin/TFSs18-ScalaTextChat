package Repo


import Utils.DBComponent
import org.slf4j.LoggerFactory


trait H2DBTest extends DBComponent {



  val logger = LoggerFactory.getLogger(this.getClass)

  val driver = slick.jdbc.H2Profile

  import driver.api._
  val h2Url = "jdbc:h2:mem:demo;MODE=MySql;INIT=runscript from 'src/test/resources/schema.sql'\\;runscript from 'src/test/resources/schemadata.sql'"

  val db: Database = {
    logger.info("Creating test connection ..................................")
    Database.forURL(url = h2Url, driver = "org.h2.Driver")
  }
}