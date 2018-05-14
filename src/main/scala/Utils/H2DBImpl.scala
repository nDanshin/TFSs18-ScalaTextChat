package Utils

import org.slf4j.LoggerFactory


trait H2DBImpl extends DBComponent {



  val logger = LoggerFactory.getLogger(this.getClass)

  val driver = slick.jdbc.H2Profile

  import driver.api._


  val h2Url ="jdbc:h2:tcp://localhost/~/bank"
  val db: Database = {
    logger.info("Creating test connection ..................................")
    Database.forConfig("tpcdi")

  }
}