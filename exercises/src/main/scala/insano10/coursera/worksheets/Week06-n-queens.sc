/*
Implement a function that will return a set of results for how to arrange n queens
on an n x n chess board without putting themselves in check
(i.e. no conflicting row, column or diagonal)

The Lists contain the column indices the queen should be placed in for that row
 */

def nQueens(n: Int): Set[List[Int]] = {

  def isSafe(col: Int, queens: List[Int]): Boolean = {

    val row = queens.length
    val queensWithRows = (row - 1 to 0 by -1).zip(queens)
    queensWithRows.forall {
      case (r, c) => c != col && Math.abs(col - c) != Math.abs(row - r)
    }
  }

  def placeQueens(k: Int): Set[List[Int]] =
    if (k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if (isSafe(col, queens))
      } yield col :: queens

  placeQueens(n)
}

def show(queens: List[Int]): String = {
  val lines =
    for (q <- queens)
      yield Vector.fill(queens.length)("*").updated(q, "X").mkString + "\n"

  "\n" + lines.mkString
}

val result = nQueens(4)
result.map(show)

/*

[-][Q][-][-]       [-][-][Q][-]
[-][-][-][Q]       [Q][-][-][-]
[Q][-][-][-]       [-][-][-][Q]
[-][-][Q][-]       [-][Q][-][-]



Note: to works out if a position would be in check on a diagonal use:

Math.abs(col - c) != Math.abs(row - r)

If the different between the columns and rows of 2 queens are equal then they must be on
2 corners of the same square, i.e. a diagonal.

 */
