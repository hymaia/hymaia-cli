package fr.hymaia
package fromagerie
import java.io.File
import scala.io.Source

object Api {
  val url = "https://pdpaci2ge1.execute-api.eu-west-1.amazonaws.com/Prod"

  def readFile(resultFile: File): String = {
    if (resultFile.exists()) {
      val source = Source.fromFile(resultFile)
      val s = source.mkString
      source.close()
      s
    } else ""
  }


  def getCredentialFile(token: String): String = {
    if (token != null) {
      return token
    }
    val homeDir = System.getProperty("user.home")
    val credentialsFile = new File(s"$homeDir/.hymaia/credentials")

    readFile(credentialsFile)
  }
}
