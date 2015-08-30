import com.tuplejump.sbt.yeoman.Yeoman

name := """twitterstream"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

Yeoman.yeomanSettings ++ Yeoman.withTemplates

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "io.argonaut" %% "argonaut" % "6.0.4",
  "org.json4s" %% "json4s-native" % "3.2.11"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator