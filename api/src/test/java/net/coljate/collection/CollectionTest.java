package net.coljate.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
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

    @Test
    default void testSpliterator() {
        assertNotNull(this.createTestCollection().spliterator());
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

        default void testCount_Element() {
            assertThat(this.createTestCollection().count(e -> Objects.equals(e, this.getCollectionElement())), is(1));
        }

        @Test
        default void testContains() {
            assertTrue(this.createTestCollection().contains(this.getCollectionElement()));
        }

        @Test
        default void testIterator() {
            final Iterator<T> iterator = this.createTestCollection().iterator();
            assertTrue(iterator.hasNext());
            assertThat(iterator.next(), is(this.getCollectionElement()));
            assertFalse(iterator.hasNext());
            assertThrows(NoSuchElementException.class, () -> iterator.next());
        }

        @Test
        default void testIterator_HasNextRepeatable() {
            final Iterator<T> iterator = this.createTestCollection().iterator();
            assertTrue(iterator.hasNext());
            assertTrue(iterator.hasNext());
            assertTrue(iterator.hasNext());
            iterator.next();
            assertFalse(iterator.hasNext());
            assertFalse(iterator.hasNext());
            assertFalse(iterator.hasNext());
        }

        @Test
        default void testIterator_NextWithoutHaveNext() {
            final Iterator<T> iterator = this.createTestCollection().iterator();
            assertThat(iterator.next(), is(this.getCollectionElement()));
            assertThrows(NoSuchElementException.class, () -> iterator.next());
        }

    }

}
