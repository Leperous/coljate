package net.ollie.sc4j;

import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings("unchecked")
public abstract class AbstractMutableMultisetTest<C extends Multiset.Mutable<Object>>
        extends AbstractCollectionTest<C> {

    @Test
    public void shouldIterate() {

        final Object o1 = new Object(), o2 = new Object();
        final C multiset = this.create(o1, o2, o1);
        final Iterator<Object> iterator = multiset.iterator();

        assertTrue(iterator.hasNext());

        final java.util.List<Object> objects = new java.util.ArrayList<>(3);
        iterator.forEachRemaining((Object o) -> objects.add(o));
        assertThat(objects, isOneOf(Arrays.asList(o1, o1, o2), Arrays.asList(o1, o2, o1), Arrays.asList(o2, o1, o1)));

    }

    @Test
    public void shouldIncrementMissing() {
        final Object o1 = new Object();
        final C multiset = this.create();
        assertFalse(multiset.contains(o1));
        assertThat(multiset.increment(o1), is(1));
        assertThat(multiset.increment(o1), is(2));
    }

    @Test
    public void shouldIncrementPresent() {
        final Object o1 = new Object();
        final C multiset = this.create(o1);
        assertThat(multiset.increment(o1), is(2));
        assertThat(multiset.increment(o1), is(3));
        assertThat(multiset.increment(o1), is(4));
    }

    @Test
    public void shouldDecrementMissing() {
        final Object o1 = new Object();
        final C multiset = this.create();
        assertFalse(multiset.contains(o1));
        assertThat(multiset.decrement(o1), is(0));
    }

    @Test
    public void shouldDecrementPresent() {
        final Object o1 = new Object();
        final C multiset = this.create(o1);
        assertThat(multiset.decrement(o1), is(0));
        assertThat(multiset.decrement(o1), is(0));
    }

}
