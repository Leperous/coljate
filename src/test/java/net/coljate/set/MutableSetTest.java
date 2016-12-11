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
    MutableSet<T> create(T... elements);

    @Test
    default void testAdd_Twice() {
        final T element = this.createObject();
        final MutableSet<T> set = this.create(element);
        assertFalse(set.add(element));
    }

}
