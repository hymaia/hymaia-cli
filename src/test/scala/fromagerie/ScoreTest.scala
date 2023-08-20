package fr.hymaia
package fromagerie

import org.scalatest.GivenWhenThen
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ScoreTest extends AnyFunSuite with GivenWhenThen with Matchers {
  test("parsePlayerScoresList parse json to a list of PlayerScore") {
    Given("a one line json")
    val input = """{"message": "Voici le top 10 des joueurs ainsi que votre position par rapport à eux", "player_score": {"player": "titi", "score": -514059885, "rank": 2},"top": [{"player": "toto", "score": 0, "rank": 1},{"player": "titi", "score": -514059885, "rank": 2}]}"""
    val expected = List(PlayerScore("titi", -514059885, 2), PlayerScore("toto", 0, 1), PlayerScore("titi", -514059885, 2))

    When("I parse the json")
    val actual = Score.parsePlayerScoresList(input)

    Then("I have a list of ScorePlayer")
    actual shouldEqual expected
  }
}
