package net.coljate.set;

import net.coljate.collection.CollectionTest;

/**
 *
 * @author Ollie
 */
public interface SetTest<T> extends CollectionTest<T> {

    @Override
    Set<T> create(java.util.List<T> elements);

}
