package net.coljate.set;

import static org.junit.Assert.assertFalse;
import org.junit.Test;

import net.coljate.collection.MutableCollectionTest;

/**
 *
 * @author ollie
 */
public interface MutableSetTest<T> extends SetTest<T>, MutableCollectionTest<T> {

    @Override
    MutableSet<T> create(java.util.List<T> elements);

    default MutableSet<T> create(final T element) {
        return this.create(java.util.Arrays.asList(element));
    }

    @Test
    default void testAdd_Twice() {
        final T element = this.createObject();
        final MutableSet<T> set = this.create(element);
        assertFalse(set.add(element));
    }

}
