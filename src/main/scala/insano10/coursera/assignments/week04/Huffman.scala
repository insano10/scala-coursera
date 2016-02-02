package insano10.coursera.assignments.week04

import scala.annotation.tailrec

object Huffman {

  abstract class CodeTree

  case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree

  case class Leaf(char: Char, weight: Int) extends CodeTree

  def weight(tree: CodeTree): Int = tree match {

    case Fork(_, _, _, weight) => weight
    case Leaf(_, weight) => weight
  }

  def chars(tree: CodeTree): List[Char] = tree match {

    case Fork(_, _, chars, _) => chars
    case Leaf(char, _) => List(char)
  }

  def makeCodeTree(left: CodeTree, right: CodeTree) =
    Fork(left, right, chars(left) ::: chars(right), weight(left) + weight(right))


  def times(chars: List[Char]): List[(Char, Int)] = {

    def accTimes(chars: List[Char], acc: List[(Char, Int)]): List[(Char, Int)] = chars match {
      case Nil => acc
      case x :: xs => accTimes(
        chars.filterNot(c => c == x),
        (x, chars.count(c => c == x)) :: acc)
    }

    accTimes(chars, List())
  }

  def makeOrderedLeafList(freqs: List[(Char, Int)]): List[Leaf] = {

    val freqsInAscendingOrder = freqs.sortWith((e1, e2) => e1._2 < e2._2)

    freqsInAscendingOrder.map(e => Leaf(e._1, e._2))
  }

  def singleton(trees: List[CodeTree]): Boolean = trees.length == 1

  def combine(trees: List[CodeTree]): List[CodeTree] = {

    trees match {
      case x :: y :: xs => (makeCodeTree(x, y) :: xs).sortWith((t1, t2) => weight(t1) < weight(t2))
      case xs => xs
    }
  }

  @tailrec
  def until(p: List[CodeTree] => Boolean, combiner: List[CodeTree] => List[CodeTree])(trees: List[CodeTree]): List[CodeTree] = {

    if (p(trees)) trees
    else until(p, combiner)(combiner(trees))
  }

  def createCodeTree(chars: List[Char]): CodeTree = {

    val freqs = times(chars)
    val leafList = makeOrderedLeafList(freqs)

    until(singleton, combine)(leafList).head
  }

  type Bit = Int

  def decode(tree: CodeTree, bits: List[Bit]): List[Char] = {

    def iterate(b: List[Bit], treePointer: CodeTree): List[Char] = {

      treePointer match {
        case Leaf(c, _) => if (b.isEmpty) List(c) else c :: iterate(b, tree)
        case Fork(left, right, _, _) => if (b.head == 0) iterate(b.tail, left) else iterate(b.tail, right)
      }
    }

    iterate(bits, tree)
  }

  def encode(tree: CodeTree)(text: List[Char]): List[Bit] = {

    def encodeChar(treePointer: CodeTree)(char: Char): List[Bit] = treePointer match {

      case Leaf(_, _) => Nil
      case Fork(left, right, _, _) =>
        if(chars(left).contains(char)) 0 :: encodeChar(left)(char)
        else 1 :: encodeChar(right)(char)
    }

    val encodeFunc = encodeChar(tree)

    //turn each Char into a List[Bit] and flatmap them all together
    text.flatMap(c => encodeFunc(c))
  }

}