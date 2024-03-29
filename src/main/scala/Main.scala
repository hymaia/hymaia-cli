package fr.hymaia

import fromagerie.FromagerieVirtuelle

import picocli.CommandLine
import picocli.CommandLine.{Command, HelpCommand}

@Command(name = "hymaia", version = Array("4.1.4"),
  subcommands = Array(classOf[FromagerieVirtuelle], classOf[HelpCommand]),
  description = Array("Hymaia CLI to get fun data about us"))
class Main extends Runnable {
  @CommandLine.Spec
  val spec: CommandLine.Model.CommandSpec = null

  def run(): Unit = {
    throw new CommandLine.ParameterException(spec.commandLine(), "Specify a subcommand")
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    System.exit(new CommandLine(new Main()).execute(args: _*))
  }
}