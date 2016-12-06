package net.coljate.set;

import net.coljate.collection.ImmutableCollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class ImmutableSetTest extends ImmutableCollectionTest {

    @Override
    protected abstract <T> ImmutableSet<T> create(T... elements);

    @Test
    public void testWith_Singleton() {
        final Object o1 = new Object(), o2 = new Object();
        final ImmutableSet<Object> s1 = Set.of(o1);
        final ImmutableSet<Object> s2 = s1.with(o2);
        assertThat(s1, is(not(s2)));
        assertTrue(s1.contains(o1));
        assertFalse(s1.contains(o2));
        assertTrue(s2.contains(o1));
        assertTrue(s2.contains(o2));
    }

    @Test
    public void testWith_Singleton_Same() {
        final Object o1 = new Object();
        final ImmutableSet<Object> s1 = Set.of(o1);
        assertThat(s1.with(o1), sameInstance(s1));
    }

}
