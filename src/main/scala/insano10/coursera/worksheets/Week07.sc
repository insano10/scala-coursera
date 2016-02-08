
//Streams are like lazy lists that only evaluate the tail on demand
val s = Stream(1,2,3,4,5)
s.map(_*2)

//cons values onto streams
(0 #:: s).toList

def divisibleBy3(n: Int) = {
  println("called function")
  n % 3 == 0
}

//using a stream we only need to execute the filter 3 times before we find the first value
val st = (1000 to 10000).toStream.filter(divisibleBy3)
val a = st(3)
val b = st(3)
val c = st(3)

//with a standard Range we would execute the filter 9000 times and throw it all away
(1000 to 10000).filter(divisibleBy3).head


//we can only call it once (and reuse the value) with lazy evaluation
val addNums = {
  def x = {println("adding"); 2 + 2}
  x + x
}
addNums

val addNumsLazy = {
  lazy val x = {println("adding"); 2 + 2}
  x + x
}
addNumsLazy

