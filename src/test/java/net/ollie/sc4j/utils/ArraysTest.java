package net.ollie.sc4j.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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

}
