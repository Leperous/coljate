package net.coljate.collection;

import net.coljate.Tests;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public interface CollectionTests<T> extends Tests<T> {

    Collection<T> create(T... elements);

}
