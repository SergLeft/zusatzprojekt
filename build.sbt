ThisBuild / version := "beta"

ThisBuild / scalaVersion := "3.3.7"

lazy val root = (project in file("."))
  .settings(
    name := "sparse"
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.9" % Test
)

Compile / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.Flat

Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/report")
