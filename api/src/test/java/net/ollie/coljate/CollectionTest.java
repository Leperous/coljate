package net.ollie.coljate;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class CollectionTest<T> implements CollectionBuilder<T> {

    protected abstract T randomValue();

    @Test
    public void testEmpty_IsEmpty() {
        final Collection<T> empty = this.create();
        assertTrue("Should be empty", empty.isEmpty());
    }

    @Test
    public void testEmpty_Count() {
        final Collection<T> empty = this.create();
        assertThat("Should have 0 size", empty.count(), is(0));
    }

    @Test
    public void testEmpty_HeadTail() {
        final Collection<T> empty = this.create();
        assertNull(empty.head());
        assertThat("Should have empty tail", empty.tail(), is(empty));
    }

    @Test
    public void testEmpty_ToArray() {
        final Collection<T> empty = this.create();
        final Object[] array = empty.toArray();
        assertThat(array.length, is(0));
    }

    @Test
    public void testSingleton_Count() {
        final T random = this.randomValue();
        final Collection<T> singleton = this.create(random);
        assertFalse("Should not be empty", singleton.isEmpty());
        assertThat("Should have 1 size", singleton.count(), is(1));
    }

    @Test
    public void testSingleton_Contains() {
        final T random = this.randomValue();
        final Collection<T> singleton = this.create(random);
        assertTrue("Contains element", singleton.contains(random));
    }

    @Test
    public void testSingleton_Iterator() {
        final T random = this.randomValue();
        final Iterator<T> iterator = this.create(random).iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(random));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSingleton_HeadTail() {
        final T head = this.randomValue();
        final Collection<T> singleton = this.create(head);
        assertThat("Head", singleton.head(), is(head));
        assertThat("Tail", singleton.tail(), is(this.create()));
    }

    @Test
    public void testSingleton_ToArray() {
        final T head = this.randomValue();
        final Collection<T> collection = this.create(head);
        final Object[] array = collection.toArray();
        assertThat("Array length", array.length, is(1));
        assertThat("First element in array", array[0], is(head));
    }

    @Test
    public void testDual_Count() {
        final T first = this.randomValue(), second = this.randomValue();
        final Collection<T> collection = this.create(first, second);
        assertFalse("Collection is not empty", collection.isEmpty());
        assertThat("Collection size", collection.count(), is(2));
    }

}
