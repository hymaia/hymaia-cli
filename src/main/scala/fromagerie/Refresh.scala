package fr.hymaia
package fromagerie

import picocli.CommandLine.{Command, Option}
import scalaj.http.Http

@Command(name = "refresh", description = Array("Récupérer son token pour jouer"))
class Refresh extends Runnable {
  @Option(names = Array("-t", "--token"), required = true, description = Array("Jeton de rafraichissement donné lors de la connexion"))
  private var token: String = _

  override def run(): Unit = {
    val res = Http(s"${Api.url}/signin/refresh").postData(
      s"""{"refresh_token": "$token"}"""
    ).header("Content-Type", "application/json").asString
    println(res.body)
  }
}
