package fr.hymaia
package fromagerie


import picocli.CommandLine
import picocli.CommandLine.{Command, HelpCommand}

@Command(name = "fromagerie", description = Array("Jouer à la fromagerie virtuelle"),
  subcommands = Array(classOf[Signup], classOf[Confirm], classOf[Signin], classOf[Refresh], classOf[SendResult], classOf[Score], classOf[HelpCommand]))
class FromagerieVirtuelle extends Runnable {
  @CommandLine.Spec
  val spec: CommandLine.Model.CommandSpec = null

  def run(): Unit = {
    throw new CommandLine.ParameterException(spec.commandLine(), "Specify a subcommand")
  }
}
