name := "test-service"
organization := "com.test"

scalaVersion := "2.12.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaVersion = "2.5.3"
  val akkaHttpVersion = "10.1.3"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  )
}

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") =>
    MergeStrategy.first
  case PathList("javax", "ws", "rs", _*) =>
    MergeStrategy.first
  case PathList("org", "apache", "commons", "collections", _*) =>
    MergeStrategy.first
  case PathList("org", "apache", "commons", "logging", _*) =>
    MergeStrategy.first
  case PathList("org", "apache", "log4j", _*) =>
    MergeStrategy.first
  case x =>
    val fallbackStrategy = (assemblyMergeStrategy in assembly).value
    fallbackStrategy(x)
}

enablePlugins(BuildInfoPlugin)
buildInfoKeys := Seq[BuildInfoKey](
  name, 
  version, 
  scalaVersion,
  sbtVersion,
  "githash" -> git.gitHeadCommit.value.getOrElse("")
)
buildInfoPackage := "build"

parallelExecution in Test := false
testForkedParallel in Test := false

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)
PB.deleteTargetDirectory := false

