package net.ollie.coljate.utils;

import net.ollie.coljate.utils.Arrays;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public class ArraysTest {

    public ArraysTest() {
    }

    @Test
    public void shouldPrefix() {

        final Object o1 = new Object(), o2 = new Object();

        final Object[] array = {o1};
        final Iterable<Object> iterable = java.util.Arrays.asList(o2);

        final Object[] prefixed = Arrays.concatenate(iterable, array);
        assertThat(prefixed[0], is(o2));
        assertThat(prefixed[1], is(o1));

    }

    @Test
    public void shouldSuffix() {

        final Object o1 = new Object(), o2 = new Object();

        final Object[] array = {o1};
        final Iterable<Object> iterable = java.util.Arrays.asList(o2);

        final Object[] prefixed = Arrays.concatenate(array, iterable);
        assertThat(prefixed[0], is(o1));
        assertThat(prefixed[1], is(o2));

    }

    @Test
    public void testIterator() {

        final Object o1 = new Object(), o2 = new Object();
        final Object[] array = new Object[]{o1, null, o2};
        final Iterator<Object> iterator = Arrays.iterator(array);

        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(o1));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(o2));
        assertFalse(iterator.hasNext());

    }

}
