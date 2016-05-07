/*
Define a function to tell if a list of characters contains balanced parenthese

  true:
  (if (zero? x) max (/ 1 x))
  I told him (that it’s not (yet) done). (But he wasn’t listening)

  false:
  :-)
  ())(


 */

def balanced(chars: List[Char]): Boolean = {

  def iterate(c: List[Char], unclosed: Int): Boolean = {

    println(s"c=$c unclosed=$unclosed")
    if (c.isEmpty) unclosed == 0
    else if (c.head == '(') iterate(c.tail, unclosed + 1)
    else if (c.head == ')') if (unclosed > 0) iterate(c.tail, unclosed - 1) else false
    else iterate(c.tail, unclosed)
  }

  iterate(chars, 0)
}


balanced("(if (zero? x) max (/ 1 x))".toList)
balanced("I told him (that it’s not (yet) done). (But he wasn’t listening)".toList)

balanced(":-)".toList)
balanced("())(".toList)
