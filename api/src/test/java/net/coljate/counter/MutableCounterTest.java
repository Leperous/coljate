package net.coljate.counter;

import net.coljate.collection.MutableCollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
 */
public interface MutableCounterTest<T> extends CounterTest<T>, MutableCollectionTest<T> {

    @Override
    MutableCounter<T> createTestCollection();

    interface ZeroElementTests<T>
            extends MutableCounterTest<T>, CounterTest.ZeroElementTests<T>, MutableCollectionTest.ZeroElementTests<T> {

    }

    interface OneElementTests<T>
            extends MutableCounterTest<T>, CounterTest.OneElementTests<T>, MutableCollectionTest.OneElementTests<T> {

        @Test
        default void testSet() {
            final MutableCounter<T> counter = this.createTestCollection();
            counter.set(this.getCollectionElement(), 5);
            assertThat(counter.count(this.getCollectionElement()), is(5));
        }

        @Test
        default void testSet_Negative() {
            final MutableCounter<T> counter = this.createTestCollection();
            assertThrows(IllegalArgumentException.class, () -> counter.set(this.getCollectionElement(), -5));
            assertThat(counter.count(this.getCollectionElement()), is(1));
        }

        @Test
        default void testSet_Inserts() {
            final MutableCounter<T> counter = this.createTestCollection();
            final T newElement = this.createTestObject();
            counter.set(newElement, 2);
            assertThat(counter.count(newElement), is(2));
        }

    }

}
