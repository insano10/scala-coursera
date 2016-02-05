
//Vectors
val foo = Vector(1,2,3)
foo(1)

//append
val foo2 = foo :+ 4

//prepend
val foo3 = 0 +: foo2

foo3.map(e => e + 1)

//fill
val filledVector = Vector.fill(10)("* ")

//Strings are sequences too
val str = "Hello World"

val capitalLetters = str.filter(c => c.isUpper)
val highestChar = str.reduce((c1, c2) => c1.max(c2))
str.head
str.tail

//So are Ranges
(0 to 10 by 2).map(n => n * 10)

//Some operations on sequences

//one element?
str.exists(c => c.isUpper)

//all elements?
str.forall(c => c.isUpper)

//combine 2 sequences into 1 sequence of pairs
val seq1 = List(1,2,3,4,5)
val seq2 = Vector("a", "b", "c")

seq1.zip(seq2)
seq2.zip(seq1)

//split a sequence of pairs into 2 separate sequences
val pairs = List((1,2), (3,4), (5,6), (7,8), (9,10))
pairs.unzip

//flatmap turns an element into a List, then concatenates all the Lists together
str.flatMap(c => List(c, '.'))
pairs.flatMap(e => List(e._1, e._2))

val seq = "thiswordissquashedtogether"
seq.flatMap(c => List(c, " ")).mkString

//passing a list of lists through flatmap will collapse it into a single list
val listOfLists = List(List(1,2,3), List(4,5,6), List(7,8,9))
listOfLists.flatMap(identity)

//this can also be achieved by calling flatten
listOfLists.flatten

//exercises

//return all the pairs (x,y) where x comes from seqA and y from seqB
val seqA = List(1,2,3,4,5)
val seqB = List(6,7,8,9,0)

seqA.flatMap(x => seqB.map(y => (x, y)))

//write a function to determine whether a number is prime
def isPrime(n: Int): Boolean =
  !(2 until n).exists(x => n%x == 0)

isPrime(1)
isPrime(2)
isPrime(3)
isPrime(4)
isPrime(5)
isPrime(6)







