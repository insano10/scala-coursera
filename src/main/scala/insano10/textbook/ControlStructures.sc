
//if statements
val n = if (2 < 3) "yup" else "nope"

//while loops
var x = 0
while (x < 5) {
  println(x)
  x = x + 1
}

x = 0
do {
  println(x)
  x = x + 1
} while (x < 5)

//for loops
val nums = List(1, 2, 3, 4, 5)
for (n <- nums)
  println(n)

//available on ranges too
//this will execute 10 times from 1 to 10
for (n <- 1 to 10)
  println(n)

//'until' does not include the upper bound
//this will execute 9 times from 1 to 9
for (n <- 1 until 10)
  println(n)

//you can filter in a for loop too
for (n <- nums if n % 2 == 0) {
  println("even number: " + n)
}

//nested for loops go in a single for method
val twoDNums = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
for (
  subArray <- twoDNums;
  num <- subArray
)
  println("num: " + num)


//a for loop can return a collection with the yield keyword
val doubleNumStrs: List[String] =
  for (n <- nums)
    yield (n * 2).toString

//try...catch...finally
try {

} catch {
  case ex: RuntimeException => println("hi")
} finally {
  println("done!")
}

//pattern matching
val colour = "red"
colour match {
  case "blue" => "oooh, like the sky"
  case "red" => "ewww, like blood"
  case _ => "whatevs"
}

//create a 10 x 10 multiplication table
def multiTable: String = {

  def makeRow(n: Int): String = (1 to 10).map(x => x * n).mkString(" ")

  (1 to 10).map(makeRow).mkString("\n")
}

val table = multiTable