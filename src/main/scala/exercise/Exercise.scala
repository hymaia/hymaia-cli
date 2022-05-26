package fr.hymaia
package exercise

import picocli.CommandLine
import picocli.CommandLine.{Command, HelpCommand}

@Command(name = "exercise", description = Array("Get Hymaïa exercise"),
  subcommands = Array(classOf[Python], classOf[HelpCommand]))
class Exercise extends Runnable {
  @CommandLine.Spec
  val spec: CommandLine.Model.CommandSpec = null

  def run(): Unit = {
    throw new CommandLine.ParameterException(spec.commandLine(), "Specify a subcommand")
  }
}
