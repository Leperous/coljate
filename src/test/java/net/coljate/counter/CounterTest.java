package net.coljate.counter;

import net.coljate.collection.CollectionTest;

/**
 *
 * @author ollie
 */
public interface CounterTest<T>
        extends CollectionTest<T> {

    @Override
    Counter<T> create(java.util.List<T> elements);

}
