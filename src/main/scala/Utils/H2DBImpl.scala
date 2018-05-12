package Utils

//import org.slf4j.LoggerFactory


/**
  * Only for demo
  */
trait H2DBImpl extends DBComponent {



  //val logger = LoggerFactory.getLogger(this.getClass)

  val driver = slick.driver.H2Driver

  import driver.api._


  val h2Url ="jdbc:h2:tcp://localhost/~/bank"
  val db: Database = {
   // logger.info("Creating test connection ..................................")
    Database.forConfig("tpcdi")

  }
}