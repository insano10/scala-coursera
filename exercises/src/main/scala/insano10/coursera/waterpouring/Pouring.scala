package insano10.coursera.waterpouring

/*

water pouring problem

Given a tap pouring water into a sink and a set of glasses of specified sizes
Produce a target amount of water in one of the glasses where the operations you can perform are:

1. fill a glass
2. empty a glass into the sink
3. empty a glass into another glass

glasses: 9L, 4L
target: 6L

			                9     4
1. fill 9		          9	    0
2. pour 9 into 4	    5	    4
3. empty 4		        5	    0
4. pour 9 into 4	    1	    4
5. empty 4		        1	    0
6. pour 9 into 4	    0	    1
7. fill 9		          9	    1
8. pour 9 into 4	    6	    4

 */

class Pouring(capacity: Vector[Int]) {

  //States
  type State = Vector[Int]
  val initialState = capacity.map(_ => 0)

  //Moves
  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    def change(state: State): State = state.updated(glass, 0)
  }

  case class Fill(glass: Int) extends Move {
    def change(state: State): State = state.updated(glass, capacity(glass))
  }

  case class Pour(from: Int, to: Int) extends Move {
    def change(state: State): State = {
      val amount = state(from).min(capacity(to) - state(to))
      state.
        updated(from, state(from) - amount).
        updated(to, state(to) + amount)
    }
  }

  val glasses = capacity.indices

  val moves = (for (g <- glasses) yield Empty(g)) ++
    (for (g <- glasses) yield Fill(g)) ++
    (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))

  //Paths
  class Path(history: List[Move], val endState: State) {

    def extend(move: Move): Path = new Path(move :: history, move.change(endState))

    override def toString = history.reverse.mkString(" ") + " --> " + endState
  }

  val initialPath = new Path(Nil, initialState)

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if(paths.isEmpty) Stream.empty
    else {
      val more = for{
        path <- paths
        next <- moves.map(path.extend(_))
        if !explored.contains(next.endState)
      } yield next
      paths #:: from(more, explored ++ more.map(_.endState))
    }

  val pathSets: Stream[Set[Path]] = from(Set(initialPath), Set(initialState))

  def solution(target: Int): Stream[Path] =
    for{
      pathSet <- pathSets
      path <- pathSet
      if path.endState.contains(target)
    } yield path

}
