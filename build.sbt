name := "zio-learn"

version := "0.1"

scalaVersion := "2.12.8"

resolvers += Resolver.sonatypeRepo("snapshots")

val zio = "org.scalaz" %% "scalaz-zio" % "1.0-RC5"
val utest = "com.lihaoyi" %% "utest" % "0.6.7" % "test"

libraryDependencies ++= Seq(zio, utest)
