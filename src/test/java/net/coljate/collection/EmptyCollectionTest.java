package net.coljate.collection;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Assume;
import org.junit.Test;

/**
 * Tests for collections that are empty.
 *
 * @author ollie
 */
public interface EmptyCollectionTest<T> extends CollectionTest<T> {

    @Override
    default Collection<T> create(final java.util.List<T> elements) {
        Assume.assumeTrue(elements.isEmpty());
        return this.create();
    }

    Collection<T> create();

    @Test
    default void testIsEmpty_Empty() {
        final Collection<T> collection = this.create();
        assertTrue("Collection should be empty", collection.isEmpty());
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
