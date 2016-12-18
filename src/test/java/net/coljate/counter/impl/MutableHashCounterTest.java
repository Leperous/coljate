package net.coljate.counter.impl;

import java.util.Arrays;

import net.coljate.ObjectContainerTest;
import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.counter.MutableCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableHashCounterTest
        extends ObjectContainerTest
        implements MutableCounterTest<Object>, AllCollectionSizeTest<Object> {

    @Override
    public MutableHashCounter<Object> create(final java.util.List<Object> elements) {
        return MutableHashCounter.copyOf(elements);
    }

    @Test
    public void testCount_Two() {
        final Object element = this.createObject();
        final HashCounter<Object> set = this.create(Arrays.asList(element, element));
        assertThat(set.count(element), is(2));
    }

    @Test
    public void testDecrement() {
        final Object element = this.createObject();
        final MutableHashCounter<Object> set = this.create(Arrays.asList(element, element));
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
