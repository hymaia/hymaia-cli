package fr.hymaia

import picocli.CommandLine.Command

sealed trait Value {
  val name: String
  val description: String
  val lineSize = 50

  def getDescriptionLines: List[String] = {
    description.split("\n").toList
  }

  override def toString: String = {
    val lines = getDescriptionLines
    s"""$name :
      |${lines.map(s => "\t" + s).mkString("\n")}
      |""".stripMargin
  }
}
case object Passion extends Value {
  override val name: String = "Passion"
  override val description: String =
    """Nous sommes guidés par notre passion pour notre métier et prenons du plaisir à le
      |mettre en œuvre. Cette passion pour notre expertise est notre motivation intrinsèque.
      |
      |Nous sommes tous des Data Citizens, passionnés par notre métier et en quête constante
      |de nouveauté et d’innovation. Sérieux sans se prendre au sérieux, nous célébrons chaque
      |victoire à sa juste valeur.""".stripMargin
}
case object Integrite extends Value {
  override val name: String = "Intégrité"
  override val description: String =
    """Nous sommes des êtres accomplis, qui prennent la responsabilité de leurs actions et idées.
      |
      |En tant qu’experts de la donnée, nous avons un impact fort sur les produits de nos clients
      |ainsi que sur l’environnement. Nous sommes responsables et sensibilisés aux questions
      |d’impact environnemental et éthique de notre métier et accompagnons nos clients et notre
      |communauté en ce sens.""".stripMargin
}
case object Pragmatisme extends Value {
  override val name: String = "Pragmatisme"
  override val description: String =
    """Nos convictions n’ont que très peu de valeur si elles ne sont pas partagées et débattues.
      |Nous respectons les idées de chacun et cherchons à apprendre les uns des autres.
      |
      |Nous sommes tous des êtres apprenants et perfectibles tout au long de notre carrière et de
      |notre vie. Nous respectons nos différences et en faisons des forces. Nous faisons preuve
      |d’empathie et d’écoute, et cherchons le meilleur dans l’expérience de chacun de nous.""".stripMargin
}

@Command(name = "values", description = Array("Get Hymaïa values"))
class Values extends Runnable {

  override def run(): Unit = {
    println(Passion)
    println(Integrite)
    println(Pragmatisme)
  }
}
