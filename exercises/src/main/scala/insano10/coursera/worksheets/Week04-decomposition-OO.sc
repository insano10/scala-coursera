trait Expr {

  def eval: Int
  def show: String
}

class Num(n: Int) extends Expr {

  override def eval: Int = n

  override def show: String = n.toString
}

class Sum(e1: Expr, e2: Expr) extends Expr {

  override def eval: Int = e1.eval + e2.eval

  override def show: String = e1.eval + " + " + e2.eval
}

val sumExpr = new Sum(new Num(2), new Num(3))

sumExpr.eval
sumExpr.show
