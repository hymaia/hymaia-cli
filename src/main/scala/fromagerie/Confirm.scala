package fr.hymaia
package fromagerie

import picocli.CommandLine.{Command, Option}
import scalaj.http.Http

@Command(name = "confirm", description = Array("Confirmer son adresse mail"))
class Confirm extends Runnable {
  @Option(names = Array("-u", "--username"), required = true, description = Array("Pseudo utilisé pour le jeu"))
  private var username: String = _

  @Option(names = Array("-c", "--verification-code"), required = true, description = Array("Le code de confirmation reçu par mail qui doit faire 6 digits"))
  private var confirmationCode: String = _

  override def run(): Unit = {
    val res = Http(s"${Api.url}/confirm").postData(
      s"""{"username": "$username", "verification_code": "$confirmationCode"}"""
    ).header("Content-Type", "application/json").asString
    println(res.body)
  }
}
