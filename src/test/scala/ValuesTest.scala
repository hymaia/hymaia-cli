package fr.hymaia

import org.scalatest.GivenWhenThen
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ValuesTest extends AnyFunSuite with GivenWhenThen with Matchers {
  test("Get pretty string from a value") {
    Given("a value")
    val input = Passion
    val expected =
      s"""Passion :
         |\tNous sommes guidés par notre passion pour notre métier et prenons du plaisir à le
         |\tmettre en œuvre. Cette passion pour notre expertise est notre motivation intrinsèque.
         |\t
         |\tNous sommes tous des Data Citizens, passionnés par notre métier et en quête constante
         |\tde nouveauté et d’innovation. Sérieux sans se prendre au sérieux, nous célébrons chaque
         |\tvictoire à sa juste valeur.
         |""".stripMargin

    When("I call toString")
    val actual = input.toString

    Then("I find description and name in same string")
    actual shouldEqual expected
  }
}
