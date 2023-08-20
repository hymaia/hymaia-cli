name := "hymaia"

version := "0.1"

scalaVersion := "2.13.8"

idePackagePrefix := Some("fr.hymaia")

libraryDependencies ++= Seq(
  "info.picocli" % "picocli" % "4.7.3",
  "info.picocli" % "picocli-codegen" % "4.7.3" % "provided",
  "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  "org.scalaj" % "scalaj-http_2.13" % "2.4.2",
)

lazy val processAnnotations = taskKey[Unit]("Process annotations")

processAnnotations := {
  val log = streams.value.log

  log.info("Processing annotations ...")

  val classpath = ((products in Compile).value ++ ((dependencyClasspath in Compile).value.files)) mkString ":"
  val destinationDirectory = (classDirectory in Compile).value
  val processor = "picocli.codegen.aot.graalvm.processor.NativeImageConfigGeneratorProcessor"
  val classesToProcess = Seq("fr.hymaia.Main") mkString " "

  val command = s"javac -cp $classpath -proc:only -processor $processor -XprintRounds -d $destinationDirectory $classesToProcess"

  failIfNonZeroExitStatus(command, "Failed to process annotations.", log)

  log.info("Done processing annotations.")
}

def failIfNonZeroExitStatus(command: String, message: => String, log: Logger) = {
  import scala.sys.process._
  val result = command !

  if (result != 0) {
    log.error(message)
    sys.error("Failed running command: " + command)
  }
}

packageBin in Compile := (packageBin in Compile dependsOn (processAnnotations in Compile)).value

graalVMNativeImageOptions := Seq(
  "--enable-https",
)

enablePlugins(GraalVMNativeImagePlugin)
