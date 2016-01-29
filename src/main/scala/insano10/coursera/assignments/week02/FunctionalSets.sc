
//Set is a function type to decide whether an integer belongs in a set
type Set = Int => Boolean
def contains(s: Set, elem: Int): Boolean = s(elem)

//1. Define a function which creates a singleton set from one integer value:
//the set represents the set of the one given element. Its signature is as follows:
def singletonSet(elem: Int): Set = x => x == elem
contains(singletonSet(5), 5)
contains(singletonSet(5), 4)


//2. Define union, intersect and diff. (diff is all the elements in s that are not in t)

def union(s: Set, t: Set): Set = x => contains(s, x) || contains(t, x)

contains(union(singletonSet(5), singletonSet(6)), 5)
contains(union(singletonSet(5), singletonSet(6)), 6)
contains(union(singletonSet(5), singletonSet(6)), 4)


def intersect(s: Set, t: Set): Set = x => contains(s, x) && contains(t, x)

contains(intersect(singletonSet(5), singletonSet(6)), 4)
contains(intersect(singletonSet(5), singletonSet(6)), 5)
contains(intersect(singletonSet(5), singletonSet(5)), 5)


def diff(s: Set, t: Set): Set = x => contains(s, x) && !contains(t, x)

contains(diff(singletonSet(5), singletonSet(6)), 5)
contains(diff(singletonSet(5), singletonSet(5)), 5)

//3. Define the function filter which selects only the elements of a set that are accepted by a given predicate p.
// The filtered elements are returned as a new set. The signature of filter is as follows:

def filter(s: Set, p: Int => Boolean): Set = intersect(s, p)

contains(filter(singletonSet(5), x => x == 5), 5)
contains(filter(singletonSet(5), x => x != 5), 5)
