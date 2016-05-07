lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.7",
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
)

lazy val root = (project in file(".")).
  aggregate(exercises, otherProject)

lazy val exercises = project.
  settings(Seq(name := "scala-coursera")).
  settings(commonSettings).
  settings(libraryDependencies ++= Dependencies.all)

lazy val otherProject = project.
  settings(Seq(name := "other-project")).
  settings(commonSettings).
  settings(libraryDependencies ++= Dependencies.all)
