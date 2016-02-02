package insano10.coursera.assignments.week04

import insano10.coursera.assignments.week04.Huffman._

object HuffmanCodingMain {

  /*
              ABCD
             /     \
         ABC        D
        /   \
      A     BC
           /   \
          B    C


   */

  def main(args: Array[String]): Unit = {

    val tree =
      makeCodeTree(
        makeCodeTree(Leaf('A', 1),
          makeCodeTree(Leaf('B', 1), Leaf('C', 1))),
        Leaf('D', 1))

    println("weight: " + tree.weight)
    println("chars: " + tree.chars)

    val freqs: List[(Char, Int)] = Huffman.times(List('A', 'B', 'C', 'D', 'B', 'A', 'A'))
    println("freqs: " + freqs)

    val leafList: List[Leaf] = Huffman.makeOrderedLeafList(freqs)
    println("leaves: " + leafList)

    val emptyTreeList = List()
    val singletonTreeList = List(makeCodeTree(Leaf('A', 1), Leaf('B', 2)))
    val multiTreeList = List(
      makeCodeTree(Leaf('A', 4), Leaf('B', 1)),
      makeCodeTree(Leaf('C', 2), Leaf('D', 2)),
      makeCodeTree(Leaf('E', 1), Leaf('F', 1))
    )

    println("empty list is singleton?: " + singleton(emptyTreeList))
    println("singleton list is singleton?: " + singleton(singletonTreeList))
    println("multi list is singleton?: " + singleton(multiTreeList))

    println("combined multi tree list: " + combine(multiTreeList))
    println("combined until singleton multi tree list: " + until(singleton, combine)(multiTreeList))

    /*
                            ([a,d,c,b], 7)
                             /          \
                      ([a], 3)        ([d,c,b],4)
                                       /        \
                                ([c,d],2)       ([b],2)
                                /      \
                           ([c],1)    ([d],1)
     */
    println(Huffman.createCodeTree(List('A', 'B', 'C', 'D', 'B', 'A', 'A')))
  }
}
