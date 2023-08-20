package fr.hymaia
package fromagerie

import picocli.CommandLine.{Command, Option}
import scalaj.http.Http

import scala.annotation.tailrec
import scala.io.Source
import scala.util.matching.Regex.Match

trait DataScore

case class PlayerScore(player: String, score: Int, rank: Int) extends DataScore {
  override def toString: String = {
    s"Ton score : $score, ton classement : $rank"
  }
}
case object NullPlayerScore extends DataScore {
  override def toString: String = {
    "Tu n'as pas encore de score"
  }
}
case class Leaderboard(top: List[PlayerScore], player_score: DataScore) {
  override def toString: String = {
    val header = "Top 10 Players:"
    val separator = "-" * header.length

    val topScores = top.map { playerData =>
      s"Rank: ${playerData.rank}, Player: ${playerData.player}, Score: ${playerData.score}"
    }

    s"$header\n$separator\n${topScores.mkString("\n")}\n\n$player_score"
  }
}

@Command(name = "score", description = Array("Récupérer le classement des joueurs et son score"))
class Score extends Runnable {
  @Option(names = Array("-t", "--token"), required = false, description = Array("Optionnel : Token JWT donné lors de la connexion. Si le paramètre n'est pas défini, hymaia CLI ira le chercher dans le fichier ~/.hymaia/credentials dans un fichier. Vous pouvez créer le fichier comme ceci : echo $TOKEN > ~/.hymaia/credentials"))
  private var token: String = _

  override def run(): Unit = {
    val raw = Source.fromBytes(Http(s"${Api.url}/score").headers(
      Seq(("Content-Type", "application/json"),
        ("Authorization", s"Bearer ${Api.getCredentialFile(token).trim}"))
    ).asString.body.getBytes(), "UTF-8").mkString

    val allScores = Score.parsePlayerScoresList(raw)
    val playerScore =  if (!raw.contains("{}")) allScores.last else NullPlayerScore

    val leaderboard = if (playerScore != NullPlayerScore) allScores.init else allScores

    println(Leaderboard(leaderboard, playerScore))
  }
}

object Score {
  def parsePlayerScoresList(json: String): List[PlayerScore] = {
    val regex = """"player": "([^"]+)", "score": (-?\d+), "rank": (\d+)""".r
    val matches = regex.findAllMatchIn(json).toList

    @tailrec
    def createPlayerScoresListAcc(acc: List[PlayerScore], l: List[Match]): List[PlayerScore] = {
      if (l.isEmpty) {
        acc
      }
      else {
        val m = l.head
        val player = m.group(1)
        val score = m.group(2).toInt
        val rank = m.group(3).toInt
        createPlayerScoresListAcc(acc :+ PlayerScore(player, score, rank), l.tail)
      }
    }

    createPlayerScoresListAcc(List(), matches)
  }
}
