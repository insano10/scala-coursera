
val map = Map[Int, String]()

val map2 = map + (1 -> "hi")

//no need to specify the map type as it can be inferred
val initialisedMap = Map(1 -> "a", 2 -> "b", 3 -> "c")

//the arrow syntax is actually calling a method '->' on the object to the left to return
//a tuple with the thing on the right
123.->("hi")

//so the tuple example
val pair = ("hello", "world")

//could be rewritten as
val pair2 = "hello".->("world")

//or even
val pair3 = "hello" -> "world"

//update a value in a map
initialisedMap.updated(3, "d")

//also works to add new values
initialisedMap.updated(9, "i")

initialisedMap.head
initialisedMap.tail

initialisedMap.fold("")((acc, value) => acc + value.toString)
