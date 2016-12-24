package net.coljate.set;

import net.coljate.collection.CollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface SetTest<T> extends CollectionTest<T> {

    @Override
    Set<T> createTestCollection();

    interface ZeroElementTests<T> extends SetTest<T>, CollectionTest.ZeroElementTests<T> {

    }

    interface SingleElementTests<T> extends SetTest<T>, CollectionTest.OneElementTests<T> {

    }

    interface DuplicateElementTests<T> extends SetTest<T> {

        T firstElement();

        T secondElement();

        @Test
        default void testCount() {
            assertThat(this.createTestCollection().count(), is(2));
        }

        @Test
        default void testContains() {
            assertTrue(this.createTestCollection().contains(this.firstElement()));
            assertTrue(this.createTestCollection().contains(this.secondElement()));
        }

    }

}
