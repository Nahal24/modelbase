name := """modernpal-common-models"""

organization := "com.modernpal"

version := "1.0"

scalaVersion := "2.12.12"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.6.7",
  "com.typesafe.play" %% "play-json-joda" % "2.6.7",
  "com.github.nscala-time" %% "nscala-time" % "2.18.0",
  "com.fsist" %% "safepickle-jackson" % "1.0.10",
  "com.fsist" %% "safepickle-joda-time" % "1.0.10"
)
