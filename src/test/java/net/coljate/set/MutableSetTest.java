package net.coljate.set;

import java.util.Arrays;

import net.coljate.collection.MutableCollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public interface MutableSetTest<T> extends SetTest<T>, MutableCollectionTest<T> {

    @Override
    MutableSet<T> create(java.util.List<T> elements);

    @Override
    default MutableSet<T> create(final T element) {
        return this.create(java.util.Arrays.asList(element));
    }

    @Override
    default MutableSet<T> create() {
        return this.create(emptyList());
    }

    @Test
    default void testAdd_Empty_Twice() {
        final T element = this.createObject();
        final MutableSet<T> set = this.create(element);
        assertFalse(set.add(element));
    }

    @Test
    default void testAddAll_Empty() {
        final MutableSet<T> set = this.create();
        final T e1 = this.createObject(), e2 = this.createObject();
        assertTrue(set.addAll(Arrays.asList(e1, e2, e1)));
        assertThat(set.count(), is(2));
    }

}
