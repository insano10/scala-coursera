
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


//4. Implement forall using linear recursion. This wll tell whether the predicate holds for each element in the set
// Here, we consider that an integer x has the property -1000 <= x <= 1000 in order to limit the search space.

def forall(s: Set, p: Int => Boolean): Boolean = {
  def iter(a: Int): Boolean = {
    if (a == 1000) true
    else if (contains(s, a) && !p(a)) false
    else iter(a + 1)
  }
  iter(-1000)
}

forall(singletonSet(5), x => x == 5)
forall(union(singletonSet(5), singletonSet(5)), x => x == 5)
forall(union(singletonSet(5), singletonSet(6)), x => x == 5)
forall(union(singletonSet(6), singletonSet(6)), x => x == 5)

//5. Using forall, implement a function exists which tests whether a set contains at least one element
// for which the given predicate is true.

def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, x => !p(x))

/*
The key here was to say:

forall(s, p) = every element satisfies p
!forall(s, p) = it is not the case that every element satisfies p
!forall(s, x => !p(x)) = it is not the case that every element does not satisfy p. I.e. at least 1 element does

 */
exists(union(singletonSet(5), singletonSet(5)), x => x == 5)
exists(union(singletonSet(5), singletonSet(6)), x => x == 5)
exists(union(singletonSet(6), singletonSet(6)), x => x == 5)


//6. Finally, write a function map which transforms a given set into another one by applying to each
// of its elements the given function.

def mapMyAttempt(s: Set, f: Int => Int): Set = {

  def iter(a: Int, acc: Set): Set = {
    if (a == 1000) acc
    else if (contains(s, a)) iter(a + 1, union(acc, x => x == f(a)))
    else iter(a + 1, acc)
  }
  iter(-1000, x => false)
}

def map(s: Set, f: Int => Int): Set = y => exists(s, x => f(x) == y)

/*
I found this implementation on the internet. It makes much more sense as it uses exists and so builds on the
exercises.

The key here is that the new set contains an Int y if there exists an x such that f(x) == y
So the original values of the set are preserved but they are just hidden behind a filter before
accessed.

 */

contains(map(union(singletonSet(5), singletonSet(6)), x => x + 1), 5)
contains(map(union(singletonSet(5), singletonSet(6)), x => x + 1), 6)
contains(map(union(singletonSet(5), singletonSet(6)), x => x + 1), 7)