package net.coljate.collection;

import net.coljate.Tests;

/**
 *
 * @author ollie
 */
public interface CollectionTest<T> extends Tests<T> {

    Collection<T> create(java.util.List<T> elements);

}
