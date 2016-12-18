package net.coljate.counter;

import net.coljate.collection.MutableCollectionTest;

/**
 *
 * @author ollie
 */
public interface MutableCounterTest<T>
        extends CounterTest<T>, MutableCollectionTest<T> {

    @Override
    MutableCounter<T> create(java.util.List<T> elements);

}
