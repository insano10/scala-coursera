//a for expression will yield a collection type equivalent to that passed in
val l = List(1,2,3)
for(x <- l; if x > 1) yield x

val s = Set(1,2,3)
for(x <- s; if x > 1) yield x

val m = Map(1 -> "a", 2 -> "b", 3 -> "c")
for(x <- m; if x._1 > 1) yield x

/*

for numbers i, j and n where 1 <= j < i < n
find a list of all pairs (i,j) where i+j is prime

 */
def isPrime(n: Int): Boolean =
  !(2 until n).exists(n % _ == 0)

def primePairs(n: Int): Seq[(Int, Int)] =
  (1 until n).flatMap(i =>
    (1 until i).map(j => (i, j))
  ).filter(p => isPrime(p._1 + p._2))

primePairs(5)

/*
The function above is a bit hard to read.
We can improve readability by splitting it up into named vals or
perhaps using a for expression.

Here we use, multiple loops, a filter and a yield statement to the same affect
Note: you can use {} instead of () so you can write a multi line statement without
      needing semi colons
 */
def moreReadablePrimePairs(n: Int): Seq[(Int, Int)] =
  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  } yield (i, j)

moreReadablePrimePairs(5)

/*
Define a function to find the scalar product of 2 Lists
( This is the sum of all pairs xs(i) * ys(i) )
 */
def scalarProduct(xs: List[Double], ys: List[Double]) : Double =
   xs.zip(ys).
     map(p => p._1 * p._2).
     sum

scalarProduct(List(1,2,3), List(4,5,6))

//debatable whether this is more readable or not...
def moreReadableScalarProduct(xs: List[Double], ys: List[Double]) : Double =
  (for((x,y) <- xs.zip(ys)) yield x * y).sum

moreReadableScalarProduct(List(1,2,3), List(4,5,6))
