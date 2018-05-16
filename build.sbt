import com.typesafe.sbt.packager.MappingsHelper._
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging

enablePlugins(JavaAppPackaging)

name := "scala-chat-project"

addCommandAlias("mgm", "migration_manager/run")

addCommandAlias("mg", "migrations/run")

lazy val slickVersion = "3.2.1"

lazy val forkliftVersion = "0.3.0"

lazy val akkaVersion = "2.5.3"

lazy val akkaHttpVersion = "10.0.9"

lazy val scalaTestVersion = "3.0.4"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.12.3",
  scalacOptions += "-deprecation",
  scalacOptions += "-feature",
  resolvers ++= Seq(
    Resolver.bintrayRepo("naftoligug", "maven"),
    Resolver.sonatypeRepo("snapshots"))
)
lazy val akkaDependencies = List(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
)

lazy val loggingDependencies = List(
  "org.slf4j" % "slf4j-nop" % "1.6.4" // <- disables logging
)

lazy val slickDependencies = List(
  "com.typesafe.slick" %% "slick" % slickVersion
)

lazy val dbDependencies = List(
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion
  ,"com.h2database" % "h2" % "1.4.192"
)

lazy val forkliftDependencies = List(
  "com.liyaos" %% "scala-forklift-slick" % forkliftVersion
  ,"io.github.nafg" %% "slick-migration-api" % "0.4.1"
)

lazy val appDependencies = dbDependencies ++ loggingDependencies ++ akkaDependencies

lazy val migrationsDependencies =
  dbDependencies ++ forkliftDependencies ++ loggingDependencies

lazy val migrationManagerDependencies = dbDependencies ++ forkliftDependencies

lazy val app = Project("app",
  file("app")).dependsOn(generatedCode).settings(
  commonSettings:_*).settings {
  libraryDependencies ++= appDependencies
}

lazy val migrationManager = Project("migration_manager",
  file("migration_manager")).settings(
  commonSettings:_*).settings {
  libraryDependencies ++= migrationManagerDependencies
}

lazy val migrations = Project("migrations",
  file("migrations")).dependsOn(
  generatedCode, migrationManager).settings(
  commonSettings:_*).settings {
  libraryDependencies ++= migrationsDependencies
}

lazy val tools = Project("git-tools",
  file("tools/git")).settings(commonSettings:_*).settings {
  libraryDependencies ++= forkliftDependencies ++ List(
    "com.liyaos" %% "scala-forklift-git-tools" % forkliftVersion,
    "com.typesafe" % "config" % "1.3.0",
    "org.eclipse.jgit" % "org.eclipse.jgit" % "4.0.1.201506240215-r"
  )
}

lazy val generatedCode = Project("generate_code",
  file("generated_code")).settings(commonSettings:_*).settings {
  libraryDependencies ++= slickDependencies
}

lazy val tutorial = Project("packager", file("."))
  .aggregate(app, migrations, migrationManager, generatedCode, tools)
  .dependsOn(app)
  .settings(commonSettings:_*)
  .settings(mainClass in Compile := Some("Server"))

// -- mappings for the database migrations --
mappings in Universal ++= contentOf(baseDirectory.value / "migrations").map {
  case (file, dest) => file -> s"db/migrations/$dest"
}
mappings in Universal ++= contentOf(baseDirectory.value / "migration_manager").map {
  case (file, dest) => file -> s"db/migration_manager/$dest"
}
mappings in Universal ++= contentOf(baseDirectory.value / "generated_code").map {
  case (file, dest) => file -> s"db/generated_code/$dest"
}
mappings in Universal ++= contentOf(baseDirectory.value / "project").map {
  case (file, dest) => file -> s"db/project/$dest"
}
mappings in Universal ++= contentOf(baseDirectory.value / "app" / "src" / "main" / "resources").map {
  case (file, dest) => file -> s"db/app/src/main/resources/$dest"
}
mappings in Universal += {
  ((baseDirectory in Compile).value / "build.sbt") -> "db/build.sbt"
}
