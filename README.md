coljate
=======

Collections library for Java 8+

Inspired by the SLF4J re-working of loggers, this project aims to redefine Java's collections API by:

  * More strongly defining mutable, immutable and unmodifiable collections.
  * Not having unsuitable methods in the API, such as mutations on immutable collections.
  * Having more suitable methods in the API, such as putIfAbsent on non-concurrent maps.
  * Baking-in functional methods on the collections themselves, and in doing so preserve return type.
  * Unifying hitherto disparate methods and interfaces, such as unifying maps keyed by integers and arrays.
  * Maximizing extensibility and minimizing repetition by taking advantage of mixin.
  * Remaining open for extension.
  * Annotating methods with [FindBugs](https://code.google.com/p/findbugs/) annotations to assist static nullity checks.
