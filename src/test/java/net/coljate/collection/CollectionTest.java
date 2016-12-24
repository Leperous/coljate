package net.coljate.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import net.coljate.TestObjectCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author ollie
 */
public interface CollectionTest<T> {

    Collection<T> createTestCollection();

    interface ZeroElementTests<T> extends CollectionTest<T> {

        @Test
        default void testCount() {
            assertThat(this.createTestCollection().count(), is(0));
        }

        @Test
        default void testIsEmpty() {
            assertTrue(this.createTestCollection().isEmpty());
        }

        @Test
        default void testIterator() {
            final Iterator<T> iterator = this.createTestCollection().iterator();
            assertFalse(iterator.hasNext());
            assertThrows(NoSuchElementException.class, () -> iterator.next());
        }

    }

    interface OneElementTests<T> extends CollectionTest<T>, TestObjectCreator<T> {

        @Test
        default void testCount() {
            assertThat(this.createTestCollection().count(), is(1));
        }

        @Test
        default void testContains() {
            assertTrue(this.createTestCollection().contains(this.createTestObject()));
        }

    }

}
