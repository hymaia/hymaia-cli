package fr.hymaia
package fromagerie

import picocli.CommandLine.{Command, Option}
import scalaj.http.Http

import java.io.File
@Command(name = "sendresult", description = Array("Envoyer son fichier json de résultat"))
class SendResult extends Runnable {
  @Option(names = Array("-t", "--token"), required = false, description = Array("Optionnel : Token JWT donné lors de la connexion. Si le paramètre n'est pas défini, hymaia CLI ira le chercher dans le fichier ~/.hymaia/credentials dans un fichier. Vous pouvez créer le fichier comme ceci : echo $TOKEN > ~/.hymaia/credentials"))
  private var token: String = _

  @Option(names = Array("-f", "--file"), required = true, description = Array("Le chemin relatif vers le fichier contenant vos prévisions de productions de fromages"))
  private var file: String = _

  override def run(): Unit = {
    val pwd = System.getProperty("user.dir")
    val resultFile = new File(s"$pwd/$file")
    println(resultFile)
    val solution = Api.readFile(resultFile)
    val res = Http(s"${Api.url}/result").postData(solution).headers(Seq(("Content-Type", "application/json"),
      ("Authorization", s"Bearer ${Api.getCredentialFile(token).trim}"))).asString
    println(res.body)
  }
}
