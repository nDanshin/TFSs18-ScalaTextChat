name := "ScalaTextChat"

version := "0.1"

scalaVersion := "2.12.5"

libraryDependencies ++= {
  val akkaV       = "2.5.3"
  val akkaHttpV   = "10.0.9"
  val scalaTestV  = "3.0.1"
  val slickVersion   = "3.2.1"
  Seq(
    "mysql"                %     "mysql-connector-java"     %      "5.1.36",
    "com.h2database"      % "h2"              % "1.4.185",
    "com.typesafe.slick"%% "slick"                % slickVersion,
    "com.typesafe.slick"%% "slick-hikaricp"       % slickVersion,
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,
    //"com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
    "com.h2database"       % 	   "h2"                       %      "1.4.196",
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV,
    "org.java-websocket" % "Java-WebSocket" % "1.3.0"
    //"org.scalatest"     %% "scalatest" % scalaTestV % "test"
  )
}