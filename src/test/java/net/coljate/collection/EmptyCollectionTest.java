package net.coljate.collection;

import static org.hamcrest.CoreMatchers.is;
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
    default Collection<T> create(final T... elements) {
        Assume.assumeTrue(elements.length == 0);
        return this.createEmpty();
    }

    Collection<T> createEmpty();

    @Test
    default void testIsEmpty_Empty() {
        final Collection<T> collection = this.createEmpty();
        assertTrue(collection.isEmpty());
    }

    @Test
    default void testCount_Empty() {
        final Collection<T> collection = this.createEmpty();
        assertThat(collection.count(), is(0));
    }

}
