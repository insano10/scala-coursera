/*
Define a function to calculate the number at any point in Pascal's triangle

      1
     1 1
    1 2 1
   1 3 3 1
  1 4 6 4 1
 1 5 10 10 5 1
1 6 15 20 15 6 1
   ...

   e.g.

   pascal(0,2)=1,
   pascal(1,2)=2
   pascal(1,3)=3

   pascal(3, 5)=10
 */

def pascal(c: Int, r: Int): Int = {
  println(s"c=$c r=$r")
  if (c == 0 || c == r) 1
  else pascal(c - 1, r - 1) + pascal(c, r - 1)
}

pascal(3, 5)
