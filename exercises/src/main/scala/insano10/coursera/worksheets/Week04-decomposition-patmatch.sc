trait Expr {

  def eval: Int = this match {
    case Num(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
    case Prod(e1, e2) => e1.eval * e2.eval
  }

  def show: String = this match {
    case Num(n) => n.toString
    case Var(n) => n
    case Sum(e1, e2) => e1.show + " + " + e2.show
    case Prod(e1, e2) => showProdSubExpr(e1) + " * " + showProdSubExpr(e2)
  }

  private def showProdSubExpr(e: Expr): String = e match {
    case Sum(e1, e2) => "(" + e1.show + " + " + e2.show + ")"
    case _ => e.show
  }
}

case class Num(n: Int) extends Expr { }
case class Var(n: String) extends Expr { }
case class Sum(e1: Expr, e2: Expr) extends Expr { }
case class Prod(e1: Expr, e2: Expr) extends Expr { }

val numExpr = Num(1)
val varExpr = Var("x")
val sumExpr = Sum(Num(2), Num(3))
val prodExpr = Prod(Num(4), Num(5))

numExpr.eval
numExpr.show

sumExpr.eval
sumExpr.show

prodExpr.eval
prodExpr.show

val exprWithNoBrackets = Sum(Prod(Num(2), Var("x")), Var("y"))
exprWithNoBrackets.show

val exprWithBrackets = Prod(Sum(Num(2), Var("x")), Var("y"))
exprWithBrackets.show
