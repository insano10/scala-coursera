

def sqrtIter(guess: Double, x: Double): Double =
  if (betterIsGoodEnough(guess, x)) guess
  else sqrtIter(improveGuess(guess, x), x)



def isGoodEnough(guess: Double, x: Double): Boolean =
  Math.abs(guess * guess - x) < 0.001

def betterIsGoodEnough(guess: Double, x: Double): Boolean =
  Math.abs(guess * guess - x) < x/1000



def improveGuess(guess: Double, x: Double): Double =
  (guess + x / guess) / 2

def sqrt(x: Double): Double =
  sqrtIter(1, x)

sqrt(2)
sqrt(4)

sqrt(0.001)
sqrt(0.1e-20)
sqrt(1.0e20)
sqrt(1.0e50)
