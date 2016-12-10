package net.coljate.set;

import net.coljate.collection.MutableCollectionTests;

import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public interface MutableSetTests<T> extends MutableCollectionTests<T> {

    @Override
    MutableSet<T> create(T... elements);

    @Test
    default void testAdd_Twice() {
        final T element = this.createObject();
        final MutableSet<T> set = this.create(element);
        assertFalse(set.add(element));
    }

}
