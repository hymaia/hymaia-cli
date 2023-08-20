package fr.hymaia
package fromagerie

import picocli.CommandLine.{Command, Option}
import scalaj.http.Http

@Command(name = "signin", description = Array("Récupérer son token pour jouer"))
class Signin extends Runnable {
  @Option(names = Array("-u", "--username"), required = true, description = Array("Pseudo utilisé pour le jeu"))
  private var username: String = _

  @Option(names = Array("-p", "--password"), required = true, description = Array("Le mot de passe doit faire au moins 8 caractères et contenir 1 majuscule, minuscule, chiffre et caractère spécial"))
  private var password: String = _

  override def run(): Unit = {
    val res = Http(s"${Api.url}/signin").postData(
      s"""{"username": "$username", "password": "$password"}"""
    ).header("Content-Type", "application/json").asString
    println(res.body)
  }
}
