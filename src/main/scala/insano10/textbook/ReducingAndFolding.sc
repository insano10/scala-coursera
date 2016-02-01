

//Reducing is taking a data structure of type T and squashing all the elements into a single T
// e.g.

//reduce to the largest element in the list
List(1,4,8,2,6,8,3).reduce((e1, e2) => e1.max(e2))

//reduce to the element with the largest value
Map(1 -> "foo", 2 -> "bar", 3 -> "baz").reduce((e1, e2) => if(e1._2 > e2._2) e1 else e2)






//Folding is taking a data structure of type T and squashing all the elements into a different type
// e.g.

//fold all the integers in the list into a single string
//the first parameter list is the accumulator initial value
//the second parameter list is the function to squash another value into the accumulator
List(1,2,3,4,5).fold("")((acc, value) => acc + value.toString)

Map(1 -> "foo", 2 -> "bar", 3 -> "baz").fold("")((acc, value) => acc + value.toString)
