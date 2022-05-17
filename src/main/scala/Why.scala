package fr.hymaia

import picocli.CommandLine.Command

@Command(name = "why", description = Array("Understand why Hymaïa exist"))
class Why extends Runnable {

  override def run(): Unit = {
    val txt =
      s"""
         |                                Hymaïa
         |                        We are all data citizen
         |
         |Nous aidons nos clients à mettre la data au service de leurs objectifs.
         |(et non l'inverse)
         |""".stripMargin
    println(txt)
  }
}
