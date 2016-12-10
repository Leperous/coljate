package net.coljate.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public interface EmptyCollectionTests<T> {

    Collection<T> createEmpty();

    T createObject();

    @Test
    default void testIsEmpty() {
        final Collection<T> collection = this.createEmpty();
        assertTrue(collection.isEmpty());
    }

    @Test
    default void testCount() {
        final Collection<T> collection = this.createEmpty();
        assertThat(collection.count(), is(0));
    }

}
