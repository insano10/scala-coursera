/*
Define a function to tell you how many ways you can make change of a given value
out of a specific set of coin values

e.g.

There are 4 ways to make change out of the coins [1,2]
1+1+1+1
1+1+2
2+2

countChange(3, []) = 0
countChange(3, [1]) = 1

The key here is to add the results of 2 occurrences of iterate together.
When deciding what to do with a coin there are 2 ways you can go:

1. don't use the coin at all and throw it away (case one)
2. use the coin. Deduct it's value from the total and continue with all the coins

The recursive calls here play out every split decision, throwing away (returning 0)
branches that result in either running out of coins before the total is made or
busting the total we want to end up with.

 */


def countChange(money: Int, coins: List[Int]): Int = {

  def iterate(total: Int, coinsLeft: List[Int]): Int = {

    println(s"total=$total coinsLeft=$coinsLeft")

    if (total == 0) 1
    else if (total < 0) 0
    else if (total > 0 && coinsLeft.isEmpty) 0
    else iterate(total, coinsLeft.tail) + iterate(total - coinsLeft.head, coinsLeft)
  }

  iterate(money, coins.sorted.reverse)
}

countChange(4, List())
countChange(4, List(1))
countChange(4, List(1, 2))
