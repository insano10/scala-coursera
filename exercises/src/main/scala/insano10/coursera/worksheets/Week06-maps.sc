class Poly(_terms: Map[Int, Double]) {

  //define another constructor that takes a varargs of pairs
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = _terms.withDefaultValue(0.0)

  //using concatenation
//  def +(other: Poly): Poly = {
//
//    def adjust(pair: (Int, Double)): (Int, Double) = {
//      val (exp, coeff) = pair
//      (exp, coeff + terms(exp))
//    }
//
//    new Poly(terms ++ other.terms.map(adjust))
//  }

  //using foldLeft
  def + (other: Poly) =
    new Poly(other.terms.foldLeft(terms)(addTerm))

  def addTerm(terms: Map[Int, Double], term: (Int, Double)) = {
    val (key, value) = term
    terms.updated(key, terms(key) + value)
  }

  override def toString =
    terms.toList.sorted.reverse.map { case (k, v) => s"${v}x^$k" }.mkString(" + ")
}
val p1 = new Poly(1 -> 2.0, 3 -> 4, 5 -> 6.2)
val p2 = new Poly(0 -> 3.0, 3 -> 7.0)
p1 + p2
