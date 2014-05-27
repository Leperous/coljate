coljate
=======

Collections library for Java 8+

Inspired by the SLF4J re-working of loggers, this project aims to similarly redefine Java's collections API. Like SLF4J, the idea is that different implementations - backed by java.util, Guava, GS collections, etc. - are provided through configuration.

The aims are:

  * To more strongly define mutable, immutable and unmodifiable collections.
  * To not have unsuitable methods in the API, such as mutations on immutable collections.
  * To have more suitable methods in the API, such as putIfAbsent on non-concurrent maps.
  * To bake-in functional methods on the collections themselves, and in doing so preserve return type.
  * To unify hitherto disparate methods and interfaces, such as making maps and arrays extend functions.
  * To stop breaking changes from breaking code, by introducing version numbering into package names and artifact IDs.
  * To maximize extensibility and minimize repetition by taking advantage of mixin.
  * To maximize new features whilst minimizing learning curve/migration cost.
  * To be open for extension.
