
val l = List(2, 3, 4)
val l2 = 1 :: l

val l3 = List(5, 6, 7)
val l4 = l2 ::: l3

val l6 = (1 to 10).toList

l4(3)

//how many elements satisfy the given predicate
l4.count(e => e > 4)

//remove the first 2 elements
val l5a = l4.drop(2)

//remove the last 2 elements
val l5b = l4.dropRight(2)

//remove all elements (starting from head) while the predicate is true
val l5c = l4.dropWhile(e => e < 4)

//does there exist an element in the list satisfying the predicate
l4.exists(e => e > 7)

//return elements from the list satisfying the predicate
l4.filter(e => e % 2 == 0)

//return elements that do not satisfy the predicate (i.e. remove the ones that do)
l4.filterNot(e => e % 2 == 0)

//do all elements satisfy the given predicate
l4.forall(e => e < 8)

//do something for every element in the list
l4.foreach(e => println(e))

//shorter (method reference)
l4.foreach(println)

//map each element to a different value
l4.map(e => e * 10)

//turn the list into a delimited string
l4.mkString(" - woo - ")

//sort the list with a custom comparator
l4.sortWith((e1, e2) => e2 > e1)

//reduce a list given a combiner (e.g. return the highest value in the list)
l4.reduce((e1, e2) => e1.max(e2))

l4.head
l4.tail
l4.last
l4.init //all but the last element. Why would you need this?

l4.reverse
l4.isEmpty
l4.length


