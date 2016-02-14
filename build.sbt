name := "scala-coursera"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")


//Note: %% means the scala version number gets suffixed on the end of the artifact name
//      whereas % does not do this
//Note: withSources and withJavadoc are only needed for projects not being developed in an IDE as the scala plugin
//      will automatically download them anyway
libraryDependencies ++= {

  val akkaV = "2.3.14"
  val sprayV = "1.3.3"

  Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"    withSources() withJavadoc(),

    "org.scalaz"             %% "scalaz-core"              % "7.2.0"    withSources() withJavadoc(),

    "io.spray"               %%  "spray-can"               % sprayV     withSources() withJavadoc(),
    "io.spray"               %%  "spray-routing"           % sprayV     withSources() withJavadoc(),
    "io.spray"               %%  "spray-json"              % "1.3.2"    withSources() withJavadoc(),
    "io.spray"               %%  "spray-testkit"           % sprayV     % "test",

    "com.typesafe.akka"      %%  "akka-actor"              % akkaV,
    "com.typesafe.akka"      %%  "akka-testkit"            % akkaV      % "test",

    "com.novocode"           %   "junit-interface"         % "0.11"     % "test",
    "org.scalatest"          %%  "scalatest"               % "2.2.6"    % "test"
  )
}


    