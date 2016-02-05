
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

//get returns an Option
initialisedMap.get(2)

//you can pattern match on this Option as it is a case class
initialisedMap.get(2) match {
  case Some(x) => x
  case None => "boo"
}

//collections have a groupBy function that returns a map where the key
//is the output of the grouping function and the value is a list of elements
//with the same key
val animals = List("badger", "bee", "cat", "dog")
animals.groupBy(_.head)

//maps can have default values set
val food = Map(1 -> "chocolate", 2 -> "bacon").withDefaultValue("beer")
food(1)
food.get(1)
food(3)
food.get(3)

//varargs are denoted by an asterisk
def pairsToMap[K,V](pairs: (K, V)*): Map[K,V] = pairs.toMap
pairsToMap((1,2), (3,4), (5,6))