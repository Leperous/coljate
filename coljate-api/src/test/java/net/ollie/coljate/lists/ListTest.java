package net.ollie.coljate.lists;

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
public abstract class ListTest {

    @NonNull
    protected abstract List<Object> create(Object... objects);

    protected List<Object> empty() {
        return this.create();
    }

    @Test
    public void testEmpty_Count() {
        final List<Object> empty = this.empty();
        assertTrue("Should be empty", empty.isEmpty());
        assertThat("Should have 0 size", empty.count(), is(0));
    }

    @Test
    public void testEmpty_Tail() {
        final List<Object> empty = this.empty();
        assertNull(empty.head());
        assertThat("Should have empty tail", empty.tail(), is(empty));
    }

    @Test
    public void testEmpty_Array() {
        final List<Object> empty = this.empty();
        final Object[] array = empty.toArray();
        assertThat(array.length, is(0));
    }

    @Test
    public void testSingleton_Create() {
        final Object head = new Object();
        final List<Object> singleton = this.create(head);
        assertFalse("Should not be empty", singleton.isEmpty());
        assertThat("Should have 1 size", singleton.count(), is(1));
    }

    @Test
    public void testSingleton_Tail() {
        final Object head = new Object();
        final List<Object> singleton = this.create(head);
        assertThat("Should have head", singleton.head(), is(head));
        assertThat("Should have empty tail", singleton.tail(), is(this.empty()));
    }
    
    @Test
    public void testSinglton_Array() {
        final Object head = new Object();
        final List<Object> singleton = this.create(head);
        final Object[] array = singleton.toArray();
        assertThat(array.length, is(1));
        assertThat(array[0], is(head));
    }

    @Test
    public void testDual_Create() {
        final Object first = new Object(), second = new Object();
        final List<Object> singleton = this.create(first, second);
        assertFalse("Should not be empty", singleton.isEmpty());
        assertThat("Should have 1 size", singleton.count(), is(2));
    }

    @Test
    public void testDual_Tail() {
        final Object first = new Object(), second = new Object();
        final List<Object> dual = this.create(first, second);
        assertThat(dual.head(), is(first));
        assertThat(dual.tail(), is(this.create(second)));
    }

}
