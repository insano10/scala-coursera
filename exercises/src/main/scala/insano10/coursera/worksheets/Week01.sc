import scala.annotation.tailrec

//functions and blocks

def sqrt(x: Double): Double = {

  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improveGuess(guess))

  def isGoodEnough(guess: Double): Boolean =
    Math.abs(guess * guess - x) < x / 1000

  def improveGuess(guess: Double): Double =
    (guess + x / guess) / 2

  sqrtIter(1)
}

sqrt(2)
sqrt(4)

sqrt(0.001)
sqrt(0.1e-20)
sqrt(1.0e20)
sqrt(1.0e50)


//tail recursion

@tailrec
def gcd(x: Int, y: Int): Int =
  if (y == 0) x else gcd(y, x % y)

gcd(14, 21)

def factorial(n: Int): Int =
  if (n == 1) 1 else n * factorial(n - 1)

factorial(1)
factorial(3)
factorial(30)

def tailRecursiveFactorial(n: Int): Int = {

  @tailrec
  def recurse(n: Int, total: Int): Int =
    if (n == 1) total else recurse(n - 1, n * total)

  recurse(n, 1)
}

tailRecursiveFactorial(1)
tailRecursiveFactorial(3)
tailRecursiveFactorial(5)
tailRecursiveFactorial(30)
