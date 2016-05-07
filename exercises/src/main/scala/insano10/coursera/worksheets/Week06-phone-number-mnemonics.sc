import scala.io.Source

//phone keypad mapping to chars
val mnem = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
  '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")

//flip the relationship to give a map of chars to number
val chars = mnem.flatMap {
  case (k, v) => v.flatMap(c => List((c, k)))
}

//alternatively achieved through a for expression
val chars2 = for ((num, str) <- mnem; letter <- str) yield (letter, num)

//get a list of words
val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")
val words = in.getLines().toList.filter(w => w.forall(_.isLetter))


//turn a string into it's numeric equivalent
def wordCode(str: String): String =
  str.toUpperCase.map(chars(_)).mkString

//create a map from numeric code to sequence of words that all share that code
//this is achieved with a groupby with a discriminator function that calculates the code
val wordsForNum: Map[String, Seq[String]] = words.groupBy(wordCode).withDefaultValue(Seq())


//turn a number into a set of all the possible word lists that match the number
def encode(str: String): Set[List[String]] = {

  if (str.isEmpty) Set(List())
  else {
    for {
      split <- 1 to str.length
      word <- wordsForNum(str.take(split))
      rest <- encode(str.drop(split))
    } yield word :: rest
  }.toSet
}

//same as above but nicely formatted
def translate(str: String): Set[String] =
  encode(str).map( _.mkString(" "))

translate("7225247386")
