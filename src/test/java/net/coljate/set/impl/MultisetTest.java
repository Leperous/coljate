package net.coljate.set.impl;

import java.util.Arrays;

import net.coljate.ObjectContainerTest;
import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.set.MutableSet;
import net.coljate.set.MutableSetTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MultisetTest
        extends ObjectContainerTest
        implements MutableSetTest<Object>, AllCollectionSizeTest<Object> {

    @Override
    public Multiset<Object> create(final java.util.List<Object> elements) {
        return Multiset.copyOf(elements);
    }

    @Test
    @Override
    public void testAdd_Twice() {
        final Object element = this.createObject();
        final MutableSet<Object> set = this.create(element);
        assertTrue(set.add(element));
    }

    @Test
    public void testCount_Two() {
        final Object element = this.createObject();
        final Multiset<Object> set = this.create(Arrays.asList(element, element));
        assertThat(set.count(element), is(2));
    }

    @Test
    public void testDecrement() {
        final Object element = this.createObject();
        final Multiset<Object> set = this.create(Arrays.asList(element, element));
        assertThat(set.decrement(element), is(1));
        assertThat(set.decrement(element), is(0));
        assertThat(set.decrement(element), is(0));
    }

    @Test
    @Override
    @Disabled
    public void testIteratorRemove_Singleton() {
    }

}
