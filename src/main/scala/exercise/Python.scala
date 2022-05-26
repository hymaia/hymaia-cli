package fr.hymaia
package exercise

import picocli.CommandLine.Command

@Command(name = "python", description = Array("Get python exercise"))
class Python extends Runnable {
  override def run(): Unit = {
    println(
      """Tu trouveras notre exercice python sur ce repo github :
        |https://github.com/hymaia/exercice-python
        |
        |C'est un live coding que nous te demanderons de faire le jour de l'entretien tech,
        |n'hésite pas à t'entrainer et à t'amuser à tout bidouiller autant que tu veux.
        |
        |Tu peux également nous proposer ta solution en forkant le repo et en envoyant une
        |pull request :)
        |""".stripMargin)
  }
}
