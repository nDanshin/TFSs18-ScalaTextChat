name := "ScalaTextChat"

version := "0.1"

scalaVersion := "2.12.5"

libraryDependencies ++= {
  val akkaV         = "2.5.3"
  val akkaHttpV     = "10.0.9"
  val scalaTestV    = "3.0.4"
  val slickVersion  = "3.2.1"
  Seq(
    "mysql"             %  "mysql-connector-java" %      "5.1.36",
    "com.typesafe.slick"%% "slick"                % slickVersion,
    "com.typesafe.slick"%% "slick-hikaricp"       % slickVersion,
    "com.h2database"    %	   "h2"                 %      "1.4.196",
    "de.heikoseeberger" %% "akka-http-json4s"     % "1.20.1",
    "org.json4s"        %% "json4s-jackson"       % "3.5.3",
    "ch.qos.logback"    %     "logback-classic"   %      "1.1.3",
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
  //  "com.typesafe.akka" %% "akka-testkit" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,
    "org.java-websocket" % "Java-WebSocket" % "1.3.0",
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV,
  //  "org.scalatest"     %% "scalatest" % scalaTestV % "test",
    "org.scalatest" %% "scalatest" % scalaTestV % Test
  )
}