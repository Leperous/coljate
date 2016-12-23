#coljate

[![Build Status](https://travis-ci.org/ollierob/coljate.png?branch=master)](https://travis-ci.org/ollierob/coljate)
[![Coverage Status](https://coveralls.io/repos/github/ollierob/coljate/badge.svg?branch=master)](https://coveralls.io/github/ollierob/coljate?branch=master)

Collections library for Java 8+

Inspired by the SLF4J re-working of loggers, this project aims to be functionally equivalent to Java's collection library but offer syntactic improvements by:

  * Classifying a collection as one of mutable, immutable or unmodifiable.
  * Avoiding unsuitable & unsafe methods, such as mutators on immutable collections.
  * Maximizing extensibility and minimizing repetition by using mixins.
  * Remaining open for extension.
  * Annotating methods with [FindBugs](https://code.google.com/p/findbugs/) annotations to assist static nullity checks.

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
  ↓                      ↓             ↓
ImmutableSet <--------- Set -------> MutableSet -------> ConcurrentSet
  ↓                      ↓             ↓                   ↓
ImmutableMap <--------- Map -------> MutableMap -------> ConcurrentMap
```
