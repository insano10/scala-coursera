package insano10.coursera.assignments.week06

import scala.collection.mutable
import scala.collection.mutable.Map
import scala.io.Source

object Anagrams {

  type Word = String
  type Sentence = List[Word]
  type Occurrences = List[(Char, Int)]

  def loadDictionary: List[Word] =
    Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt").
      getLines().
      toList.
      filter(w => w.forall(_.isLetter))


  val dictionary: List[Word] = loadDictionary
  val dictionaryByOccurrence = dictionary.groupBy(wordOccurrences)

  //for a given word, return a sorted list of pairs indicating the frequency of each character in ascending order
  //e.g. hello => List(('e' -> 1), ('h' -> 1), ('l' -> 2), ('o' -> 1)
  def wordOccurrences(w: Word): Occurrences =
    w.groupBy(c => c.toLower).mapValues(c => c.length).toList.sorted


  //for a given word, return all anagrams of that word
  //these words will all have the same occurence list as the original word
  def wordAnagrams(word: Word): List[Word] =
    dictionaryByOccurrence.getOrElse(wordOccurrences(word), Nil)

  //for a given occurrence list, return all the possible sublists
  //this also includes all variations on a pair. e.g. ('a',2) = List(('a',1),('a',2))
  def combinations(occurrences: Occurrences): List[Occurrences] = {

    def subCombos(pair: (Char, Int)): Occurrences = {
      for (n <- 1 to pair._2) yield (pair._1, n)
    }.toList

    occurrences.foldLeft(List(List()): List[Occurrences])((acc, pair) =>
      acc ::: (for (subPair <- subCombos(pair); o <- acc) yield (subPair :: o).sorted))
  }

  //remove the frequencies in y from Occurrences x
  def subtract(x: Occurrences, y: Occurrences): Occurrences =
    y.foldLeft(x.toMap)((acc, pair) =>
      if (pair._2 == acc(pair._1)) acc - pair._1
      else acc.updated(pair._1, acc(pair._1) - pair._2)).toList


  def wordsForOccurrences(occurrence: Occurrences): List[Word] = dictionaryByOccurrence.getOrElse(occurrence, Nil)


  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {

    def anagrams(occurrences: Occurrences): List[Sentence] = {

      if (occurrences.isEmpty) List(List())
      else for {
        combination <- combinations(occurrences)
        possibleWord <- wordsForOccurrences(combination)
        sentence <- anagrams(subtract(occurrences, combination))
      } yield possibleWord :: sentence

    }
    anagrams(wordOccurrences(sentence.mkString))
  }

  val cache: mutable.Map[Occurrences, List[Sentence]] = mutable.Map()
  def sentenceAnagramsMemo(sentence: Sentence): List[Sentence] = {

    def anagrams(occurrences: Occurrences): List[Sentence] = {

      if (cache.contains(occurrences))
        cache(occurrences)
      else {
        val sentences =
          if (occurrences.isEmpty) List(List())
          else for {
            combination <- combinations(occurrences)
            possibleWord <- wordsForOccurrences(combination)
            sentence <- anagrams(subtract(occurrences, combination))
            if combination.nonEmpty
          } yield possibleWord :: sentence

        cache += occurrences -> sentences
        sentences
      }
    }
    anagrams(wordOccurrences(sentence.mkString))
  }


  def main(args: Array[String]): Unit = {

    println(sentenceAnagramsMemo(List("i", "love", "love")))
  }
}


