package net.coljate.list;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import net.coljate.collection.MutableCollection;

/**
 *
 * @author Ollie
 */
public interface MutableArrayTest<T> extends ArrayTest<T>, MutableListTest<T> {

    @Override
    MutableArray<T> create(java.util.List<T> elements);

    @Override
    default MutableArray<T> create(final T element) {
        return this.create(java.util.Arrays.asList(element));
    }

    @Test
    default void testRemoveFirst_Singleton_ReducesCount() {
        final T element = this.createObject();
        final MutableCollection<T> collection = this.create(element);
        assertTrue(collection.removeFirst(element));
        assertThat(collection.count(), is(0));
    }

}
