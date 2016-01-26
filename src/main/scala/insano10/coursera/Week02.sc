import scala.annotation.tailrec

def sumInts(a: Int, b: Int): Int =
  if (a > b) 0 else a + sumInts(a + 1, b)

sumInts(1, 1)
sumInts(1, 10)
sumInts(2, 1)

def cube(x: Int): Int = x * x * x

cube(2)

def sumOfCubes(a: Int, b: Int): Int =
  if (a > b) 0 else cube(a) + sumOfCubes(a + 1, b)

sumOfCubes(1, 1)
sumOfCubes(1, 2)
sumOfCubes(1, 3)


def tailRecSumOfCubes(a: Int, b: Int): Int = {

  @tailrec
  def recurse(a: Int, b: Int, acc: Int): Int =
    if (a > b) acc else recurse(a + 1, b, acc + cube(a))

  recurse(a, b, 0)
}

tailRecSumOfCubes(1, 1)
tailRecSumOfCubes(1, 2)
tailRecSumOfCubes(1, 3)

// higher order function versions

def sum(f: Int => Int, a: Int, b: Int): Int =
  if (a > b) 0 else f(a) + sum(f, a + 1, b)

def id(a: Int): Int = a

sum(id, 1, 10)
sum(cube, 1, 2)


//anonymous functions

(x: Int) => x * x

sum(x => x, 1, 10)
sum(x => x * x * x, 1, 2)

def tailRecSum(f: Int => Int, a: Int, b: Int): Int = {

  @tailrec
  def recurse(a: Int, acc: Int): Int =
    if (a > b) acc else recurse(a + 1, f(a) + acc)

  recurse(a, 0)
}

tailRecSum(x => x, 1, 10)
tailRecSum(x => x * x * x, 1, 2)


//function returning another function
def intermediateSum(f: Int => Int): (Int, Int) => Int = {

  def sumF(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sumF(a + 1, b)
  sumF
}

//currying
def sumSquare = intermediateSum(x => x * x)
def sumCube = intermediateSum(x => x * x * x)

sumSquare(1, 2)
sumCube(1, 2)

intermediateSum(x => x * x)(1, 2)
intermediateSum(x => x * x * x)(1, 2)

//this is syntactic sugar to define a curried function without the nested function
def shortSum(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 0 else f(a) + shortSum(f)(a + 1, b)

shortSum(x => x)(1, 10)
shortSum(x => x * x)(1, 2)


//exercises

def product(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 1 else f(a) * product(f)(a + 1, b)

product(x => x)(1, 3)

def factorial(n: Int) =
  product(x => x)(1, n)

factorial(3)

def general(f: Int => Int)(a: Int, b: Int)(terminalVal: Int)(operator: (Int, Int) => Int): Int =
  if (a > b) terminalVal else operator(f(a), general(f)(a + 1, b)(terminalVal)(operator))

//product
general(x => x)(1, 3)(0)((x, y) => x + y)

//factorial
general(x => x)(1, 3)(1)((x, y) => x * y)

//this can be jiggled around a bit to give us mapreduce!

def mapreduce(f: Int => Int, combine: (Int, Int) => Int, terminalVal: Int)(a: Int, b: Int): Int =
  if (a > b) terminalVal else combine(f(a), mapreduce(f, combine, terminalVal)(a + 1, b))

mapreduce(x => x, (x, y) => x + y, 0)(1, 3)
mapreduce(x => x, (x, y) => x * y, 1)(1, 3)