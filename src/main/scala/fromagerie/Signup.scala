package fr.hymaia
package fromagerie

import picocli.CommandLine.{Command, Option}
import scalaj.http.Http

@Command(name = "signup", description = Array("S'inscrire au jeu"))
class Signup extends Runnable {
  @Option(names = Array("-u", "--username"), required = true, description = Array("Pseudo utilisé pour le jeu"))
  private var username: String = _

  @Option(names = Array("-e", "--email"), required = true, description = Array("L'email devra être validé par réception d'un code"))
  private var email: String = _

  @Option(names = Array("-p", "--password"), required = true, description = Array("Le mot de passe doit faire au moins 8 caractères et contenir 1 majuscule, minuscule, chiffre et caractère spécial"))
  private var password: String = _

  override def run(): Unit = {
    val res = Http(s"${Api.url}/signup").postData(
      s"""{"username": "$username", "email": "$email", "password": "$password"}"""
    ).header("Content-Type", "application/json").asString
    println(res.body)
  }
}
