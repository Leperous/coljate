package net.coljate.counter;

import net.coljate.collection.MutableCollectionTest;

/**
 *
 * @author ollie
 */
public interface MutableCounterTest<T> extends CounterTest<T>, MutableCollectionTest<T> {

    @Override
    MutableCounter<T> createTestCollection();

    interface ZeroElementTests<T>
            extends MutableCounterTest<T>, CounterTest.ZeroElementTests<T>, MutableCollectionTest.ZeroElementTests<T> {

    }

    interface OneElementTests<T>
            extends MutableCounterTest<T>, CounterTest.OneElementTests<T>, MutableCollectionTest.OneElementTests<T> {

    }

}
