package fr.hymaia

import picocli.CommandLine.{Command, Option}

sealed trait Job {
  val name: String
  val url: String
  lazy val description: String =
    s"""Tu trouveras toutes les infos sur $url,
       |en attendant on t'invite à réfléchir à cette question et à nous envoyer ta réponse
       |via notre formulaire de recrutement :)""".stripMargin
  val test: String


  def addIndentationToText(txt: String): String = {
    txt.split("\n").map(s => "\t" + s).mkString("\n")
  }

  override def toString: String = {
    val indentedDescription = addIndentationToText(description)
    val indentedTest = addIndentationToText(test)

    s"""$name :
       |$indentedDescription
       |
       |$indentedTest
       |""".stripMargin
  }
}

case object DataEngineer extends Job {
  override val name: String = "Data Engineer"
  override val url: String = "https://www.hymaia.com/jobs/data-engineer"
  override val test: String =
    """Comment aiderais-tu ton client à choisir entre deux technos ?
      |Scala Spark / Pyspark, Airflow / Stepfunction, Glue / EMR / EKS, Kafka cloud / Kinesis...
      |À toi de choisir le débat :)
      |
      |N'hésite pas à détailler, à inventer ton contexte ou à nous raconter une
      |de tes expériences. Nous lirons avec attention !
      |""".stripMargin
}

case object DataScientist extends Job {
  override val name: String = "Data Science / ML engineer"
  override val url: String = "https://www.hymaia.com/jobs/data-scientist-ml-engineer"
  override val test: String =
    """Quelle est ta démarche pour concevoir un modèle de machine learning ?
      |
      |N'hésite pas à détailler, à inventer ton contexte ou à nous raconter une
      |de tes expériences. Nous lirons avec attention !
      |""".stripMargin
}

case object DataProductManager extends Job {
  override val name: String = "Data Product Manager"
  override val url: String = "https://www.hymaia.com/jobs/data-product-manager"
  override val test: String =
    """Comment expliquerais-tu à ton collègue ce qu'est un "Produit Data" ?
      |
      |N'hésite pas à détailler, à inventer ton contexte ou à nous raconter une
      |de tes expériences. Nous lirons avec attention !
      |""".stripMargin
}

case object DataStrategist extends Job {
  override val name: String = "Data Strategist"
  override val url: String = "https://www.hymaia.com/jobs/data-strategist"
  override val test: String =
    """Comment expliquerais-tu ton métier à un client qui n'a jamais entendu
      |parler de data strategy ?
      |
      |N'hésite pas à détailler, à inventer ton contexte ou à nous raconter une
      |de tes expériences. Nous lirons avec attention !
      |""".stripMargin
}

case object DataScientologist extends Job {
  override val name: String = "Data Scientologist"
  override lazy val description: String =
    """Se dit Data Scientist parce que c'est un buzzword cool mais ne sait pas ce que
      |c'est et pense que de toute façon c'est trop abstrait et que personne ne sait
      |vraiment, comme la blockchain, le web3.0 et ces influenceurs qui veulent nous
      |vendre des NFTs (ça c'est vraiment abstrait).""".stripMargin
  override val url: String = "https://www.hymaia.com/jobs/data-strategist"
  override val test: String = ""
}

@Command(name = "join", description = Array("If you want to join us"))
class Join extends Runnable {

  @Option(names = Array("-de", "--data-engineer"), description = Array("You are only interested by data engineering job description"))
  private var dataEngineer = false

  @Option(names = Array("-ds", "--data-scientist"), description = Array("You are only interested by data scientist job description"))
  private var dataScientist = false

  @Option(names = Array("-pm", "--data-product-manager"), description = Array("You are only interested by data product manager job description"))
  private var dataProductManager = false

  @Option(names = Array("--data-strategist"), description = Array("You are only interested by data strategist job description"))
  private var dataStrategist = false

  @Option(names = Array("--data-scientologist"), description = Array("You will see what happened"))
  private var dataScientologist = false

  override def run(): Unit = {
    val txt = s"""Nos Postes :
                |Chez Hymaïa, nous recherchons avant tout des personnes passionnées, avec un état
                |d'esprit d'ouverture et de collaboration.
                |
                |%s""".stripMargin

    if (dataEngineer) {
      println(txt.format(DataEngineer))
    }
    else if (dataScientist) {
      println(txt.format(DataScientist))
    }
    else if (dataProductManager) {
      println(txt.format(DataProductManager))
    }
    else if (dataStrategist) {
      println(txt.format(DataStrategist))
    }
    else if (dataScientologist) {
      println(txt.format(DataScientologist))
    }
    else {
      println(txt.format(
        s"""$DataEngineer
           |
           |$DataScientist
           |
           |$DataProductManager
           |
           |$DataStrategist
           |""".stripMargin))
    }
  }
}
