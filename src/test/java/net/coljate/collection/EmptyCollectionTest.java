package net.coljate.collection;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests for collections that are empty.
 *
 * @author ollie
 */
public interface EmptyCollectionTest<T> extends CollectionTest<T> {

    @Override
    default Collection<T> create(final java.util.List<T> elements) {
        assumeTrue(elements.isEmpty());
        return this.create();
    }

    @Test
    default void testIsEmpty_Empty() {
        final Collection<T> collection = this.create();
        assertTrue(collection.isEmpty(), "Collection should be empty");
    }

    @Test
    default void testCount_Empty() {
        final Collection<T> collection = this.create();
        assertThat(collection.count(), is(0));
    }

    @Test
    default void testIterator_Empty() {
        final Collection<T> collection = this.create();
        final Iterator<T> iterator = collection.iterator();
        assertFalse(iterator.hasNext());
    }

}
