scalaVersion := "2.10.0"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies += "io.spray" % "spray-can" % "1.1-M7" exclude("org.scala-lang", "scala-library")

libraryDependencies += "io.spray" % "spray-routing" % "1.1-M7" exclude("org.scala-lang", "scala-library")

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.1.0"

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.1.0"