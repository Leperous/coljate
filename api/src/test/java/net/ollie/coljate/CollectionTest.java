package net.ollie.coljate;

import org.checkerframework.checker.nullness.qual.NonNull;
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
public abstract class CollectionTest<T> {

    protected abstract Collection<T> create();

    @NonNull
    protected abstract Collection<T> create(T singleton);

    @NonNull
    protected abstract Collection<T> create(T first, T second);

    protected abstract T randomValue();

    @Test
    public void testEmpty_Count() {
        final Collection<T> empty = this.create();
        assertTrue("Should be empty", empty.isEmpty());
        assertThat("Should have 0 size", empty.count(), is(0));
    }

    @Test
    public void testEmpty_Tail() {
        final Collection<T> empty = this.create();
        assertNull(empty.head());
        assertThat("Should have empty tail", empty.tail(), is(empty));
    }

    @Test
    public void testEmpty_Array() {
        final Collection<T> empty = this.create();
        final Object[] array = empty.toArray();
        assertThat(array.length, is(0));
    }

    @Test
    public void testSingleton_Create() {
        final T random = this.randomValue();
        final Collection<T> singleton = this.create(random);
        assertFalse("Should not be empty", singleton.isEmpty());
        assertThat("Should have 1 size", singleton.count(), is(1));
    }

    @Test
    public void testSingleton_Tail() {
        final T head = this.randomValue();
        final Collection<T> singleton = this.create(head);
        assertThat("Should have head", singleton.head(), is(head));
        assertThat("Should have empty tail", singleton.tail(), is(this.create()));
    }

    @Test
    public void testSingleton_Array() {
        final T head = this.randomValue();
        final Collection<T> singleton = this.create(head);
        final Object[] array = singleton.toArray();
        assertThat(array.length, is(1));
        assertThat(array[0], is(head));
    }

    @Test
    public void testDual_Create() {
        final T first = this.randomValue(), second = this.randomValue();
        final Collection<T> singleton = this.create(first, second);
        assertFalse("Should not be empty", singleton.isEmpty());
        assertThat("Should have 1 size", singleton.count(), is(2));
    }

}
