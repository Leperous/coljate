package net.ollie.coljate.lists;

import org.checkerframework.checker.nullness.qual.NonNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class ImmutableListTest {

    @NonNull
    protected abstract ImmutableList<Object> create(Object... objects);

    protected ImmutableList<Object> empty() {
        return this.create();
    }

    @Test
    public void shouldCreateEmpty() {
        final ImmutableList<Object> empty = this.empty();
        assertTrue("Should be empty", empty.isEmpty());
        assertThat("Should have 0 size", empty.size(), is(0));
        assertThat("Should have empty tail", empty.tail(), is(empty));
    }

    @Test
    public void shouldCreateSingleton() {
        final Object head = new Object();
        final ImmutableList<Object> singleton = this.create(head);
        assertFalse("Should not be empty", singleton.isEmpty());
        assertThat("Should have 1 size", singleton.size(), is(1));
        assertThat("Should have head", singleton.head(), is(head));
        assertThat("Should have empty tail", singleton.tail(), is(this.empty()));
    }

    @Test
    public void shouldCreateDual() {
        final Object first = new Object(), second = new Object();
        final ImmutableList<Object> singleton = this.create(first, second);
        assertFalse("Should not be empty", singleton.isEmpty());
        assertThat("Should have 1 size", singleton.size(), is(2));
        assertThat("Should have head", singleton.head(), is(first));
    }

}
