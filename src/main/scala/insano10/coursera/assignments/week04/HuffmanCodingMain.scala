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

    //decoding
    val secret: List[Bit] = List(0,0,1,1,1,0,1,0,1,1,1,0,0,1,1,0,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,1,1,0,1,0,1,1,0,0,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,0,1,0,0,0,1,0,1)
    val frenchCode: CodeTree = Fork(Fork(Fork(Leaf('s',121895),Fork(Leaf('d',56269),Fork(Fork(Fork(Leaf('x',5928),Leaf('j',8351),List('x','j'),14279),Leaf('f',16351),List('x','j','f'),30630),Fork(Fork(Fork(Fork(Leaf('z',2093),Fork(Leaf('k',745),Leaf('w',1747),List('k','w'),2492),List('z','k','w'),4585),Leaf('y',4725),List('z','k','w','y'),9310),Leaf('h',11298),List('z','k','w','y','h'),20608),Leaf('q',20889),List('z','k','w','y','h','q'),41497),List('x','j','f','z','k','w','y','h','q'),72127),List('d','x','j','f','z','k','w','y','h','q'),128396),List('s','d','x','j','f','z','k','w','y','h','q'),250291),Fork(Fork(Leaf('o',82762),Leaf('l',83668),List('o','l'),166430),Fork(Fork(Leaf('m',45521),Leaf('p',46335),List('m','p'),91856),Leaf('u',96785),List('m','p','u'),188641),List('o','l','m','p','u'),355071),List('s','d','x','j','f','z','k','w','y','h','q','o','l','m','p','u'),605362),Fork(Fork(Fork(Leaf('r',100500),Fork(Leaf('c',50003),Fork(Leaf('v',24975),Fork(Leaf('g',13288),Leaf('b',13822),List('g','b'),27110),List('v','g','b'),52085),List('c','v','g','b'),102088),List('r','c','v','g','b'),202588),Fork(Leaf('n',108812),Leaf('t',111103),List('n','t'),219915),List('r','c','v','g','b','n','t'),422503),Fork(Leaf('e',225947),Fork(Leaf('i',115465),Leaf('a',117110),List('i','a'),232575),List('e','i','a'),458522),List('r','c','v','g','b','n','t','e','i','a'),881025),List('s','d','x','j','f','z','k','w','y','h','q','o','l','m','p','u','r','c','v','g','b','n','t','e','i','a'),1486387)

    println(decode(frenchCode, secret))

    //encoding
    val code = encode(frenchCode)(List('h','i','m','y','n','a','m','e','i','s','j','e','n','n','y'))
    println(code)
    println(decode(frenchCode, code))
  }
}
