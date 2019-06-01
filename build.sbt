name := "flashtext"

version := "0.1"

scalaVersion := "2.12.8"

enablePlugins(JmhPlugin)

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
)
