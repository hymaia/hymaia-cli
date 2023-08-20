# Hymaïa CLI

C'est parti d'un délire sur nos valeurs qui formaient PIP avec leurs premières lettres et un collègue qui enchaine "on va créer pip install hymaia". Sauf que moi je suis un vrai développeur donc je fais pas de python :p alors j'ai décidé de faire une version Scala qui sera beaucoup plus compliqué à partager parce qu'il faut faire une compilation native avec GraalVM et après il faudra surement que je l'ajoute aux packages apt, rpm, pacman... Clairement sur ce point là `pip install hymaia` c'est plus simple :D Mais au moins je me serai bien amusé à découvrir GraalVM :) (non c'est faux c'était l'horreur)

Grâce à cette CLI vous en apprendrez plus sur [Hymaïa](https://www.hymaia.com/) avec humour et légèreté.

## Tests
Pour le moment, c'est pas bien j'en ai fait qu'un tout bidon, mais faut dire j'ai quasi que des println donc bon. Des tests E2E serait pas mal ! On verra...

Pour le lancer :
```bash
sbt test
```

## Packaging
Pour vous épargner l'installation de l'enfer de GraalVM vous me remercierez j'ai créé un Dockerfile qui s'occupe du packaging. Vous aurez besoin de [docker en mode buildkit](https://docs.docker.com/develop/develop-images/build_enhancements/) pour récupérer le binaire à la fin.
```bash
docker build -o type=local,dest=./ .
```
Un fichier nommé hymaia apparaitra à la racine du repo.

## La CLI
Tout est dans le helper :
```bash
$ ./hymaia help
Usage: hymaia [COMMAND]
Hymaia CLI to get fun data about us
Commands:
  fromagerie  Jouer à la fromagerie virtuelle
  help        Display help information about the specified command.

$ ./hymaia fromagerie help
Usage: hymaia fromagerie [COMMAND]
Jouer à la fromagerie virtuelle
Commands:
  signup      S'inscrire au jeu
  confirm     Confirmer son adresse mail
  signin      Récupérer son token pour jouer
  refresh     Récupérer son token pour jouer
  sendresult  Envoyer son fichier json de résultat
  score       Récupérer le classement des joueurs et son score
  help        Display help information about the specified command.
```

## FAQ
#### À quoi sert GraalVM ?
Grâce à GraalVM je peux produire un binaire natif linux qui n'a pas besoin d'une JVM pour fonctionner.

#### Pourquoi avoir choisi Scala et pas un langage qui fait déjà de la compilation native comme Rust ?
Parce qu'en tant que Data Engineer je fais du Scala, pas du Rust :D Mais promis si un jour un ETL distribué en Rust arrive, je m'y mettrai ^^ même si j'ai pas envie :p
