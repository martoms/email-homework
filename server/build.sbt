name := """mstomatao-10182024-homework-server"""
organization := "vauldex"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "3.3.1"

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test,
  "org.playframework" %% "play-slick" % "6.1.0",
  "org.playframework" %% "play-slick-evolutions" % "6.1.0",
  "org.postgresql" % "postgresql" % "42.7.4",
  "org.mindrot" % "jbcrypt" % "0.4",
  "io.jsonwebtoken" % "jjwt-api" % "0.12.6",
  "io.jsonwebtoken" % "jjwt-impl" % "0.12.6",
  "io.jsonwebtoken" % "jjwt-jackson" % "0.12.6",
  guice
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "vauldex.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "vauldex.binders._"
