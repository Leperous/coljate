#coljate

[![Travis branch](https://img.shields.io/travis/ollierob/coljate/master.svg)](https://travis-ci.org/ollierob/coljate)
[![Coveralls branch](https://img.shields.io/coveralls/ollierob/coljate/master.svg)](https://coveralls.io/github/ollierob/coljate?branch=master)

Collections library for Java 8+

Inspired by the SLF4J re-working of loggers, this project aims to be functionally equivalent to Java's collection library but offer syntactic improvements by:

  * Classifying a collection as one of mutable, immutable or unmodifiable.
  * Avoiding unsuitable & unsafe methods, such as mutators on immutable collections.
  * Maximizing extensibility and minimizing repetition by using mixins.
  * Remaining open for extension.
  * Annotating methods with [FindBugs](https://code.google.com/p/findbugs/) annotations to assist static nullity checks.

Supported collection types:

 * Lists
 * Arrays
 * Sets
 * Multisets
 * Maps
 * Multimaps
 * Caches
 * Trees
 * Graphs 
 * Tables

Class hierarchy for ordered collecions:

```
                                     Queue -------------> ConcurrentQueue
                                       ↑
ImmutableCollection <- Collection -> MutableCollection
  ↓                      ↓             ↓
ImmutableList <------- List -------> MutableList -------> ConcurrentList
  ↓                      ↓             ↓                    ↓
ImmutableArray <------ Array ------> MutableArray ------> ConcurrentArray
```

Class hierarchy for unordered collections:

```
ImmutableCollection <- Collection -> MutableCollection
  ↓                     ↓             ↓
ImmutableSet <-------- Set ---------> MutableSet ------> ConcurrentSet
  ↓                     ↓             ↓                   ↓
ImmutableMap <-------- Map --------> MutableMap -------> ConcurrentMap
  ↓                     ↓             ↓
ImmutableTree <------- Tree -------> MutableTree ------> ConcurrentTree
```
