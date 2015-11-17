package net.ollie.coljate.lists;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class MutableListTest extends ListTest {

    @Override
    protected abstract MutableList<Object> create(Object... objects);

    @Override
    protected MutableList<Object> empty() {
        return this.create();
    }

    @Test
    public void testEmpty_Add() {
        final MutableList<Object> list = this.empty();
        assertTrue(list.isEmpty());
        final Object element = new Object();
        list.add(element);
        assertThat(list.count(), is(1));
        assertTrue(list.contains(element));
    }

}
