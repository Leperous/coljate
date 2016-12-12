coljate
=======

Collections library for Java 8+

Inspired by the SLF4J re-working of loggers, this project aims to be functionally equivalent to Java's collection library but offer syntactic improvements by:

  * Defining mutable, immutable and unmodifiable collections.
  * Not having unsuitable methods in the API, such as mutations on immutable collections.
  * Maximizing extensibility and minimizing repetition by using mixins.
  * Remaining open for extension.
  * Annotating methods with [FindBugs](https://code.google.com/p/findbugs/) annotations to assist static nullity checks.

Wrappers exist for converting to and from:

  * java.util.Collection classes
  * [Guava](https://code.google.com/p/guava-libraries/) classes

Class hierarchies:

```
ImmutableCollection <- Collection -> MutableCollection
  |                      |             |
ImmutableList <------- List -------> MutableList
  |                      |             |
ImmutableArray <------ Array ------> MutableArray
```
