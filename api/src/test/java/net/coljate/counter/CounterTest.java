package net.coljate.counter;

import net.coljate.collection.CollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface CounterTest<T> extends CollectionTest<T> {

    @Override
    Counter<T> createTestCollection();

    @Test
    default void testCount_Missing() {
        assertThat(this.createTestCollection().count(new Object()), is(0));
    }

    interface ZeroElementTests<T> extends CounterTest<T>, CollectionTest.ZeroElementTests<T> {

    }

    interface OneElementTests<T> extends CounterTest<T>, CollectionTest.OneElementTests<T> {

    }

}
