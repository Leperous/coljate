package net.coljate.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface CollectionTest<T> {

    Collection<T> createTestCollection();

    @Test
    default void testMutableCopy() {
        assertNotNull(this.createTestCollection().mutableCopy());
    }

    @Test
    default void testImmutableCopy() {
        assertNotNull(this.createTestCollection().immutableCopy());
    }

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

    interface OneElementTests<T> extends CollectionTest<T> {

        T getCollectionElement();

        @Test
        default void testCount() {
            assertThat(this.createTestCollection().count(), is(1));
        }

        @Test
        default void testContains() {
            assertTrue(this.createTestCollection().contains(this.getCollectionElement()));
        }

    }

}
