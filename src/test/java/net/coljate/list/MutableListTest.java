package net.coljate.list;

import net.coljate.MutableCollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class MutableListTest extends MutableCollectionTest {

    @Override
    protected abstract <T> MutableList<T> create(T... elements);

    @Test
    public void testSuffix() {
        final Object o1 = new Object();
        final Object o2 = new Object();
        final MutableList<Object> list = this.create(o1);
        list.suffix(o2);
        assertThat(list.count(), is(2));
        assertThat(list, is(this.create(o1, o2)));
    }

    @Test
    public void testPrefix() {
        final Object o1 = new Object();
        final Object o2 = new Object();
        final MutableList<Object> list = this.create(o1);
        list.prefix(o2);
        assertThat(list.count(), is(2));
        assertThat(list, is(this.create(o2, o1)));
    }
    
    @Test
    public void testIterator() {
        final Object o1 = new Object();
        final Object o2 = new Object();
        final Object o3 = new Object();
        final MutableList<Object> list = this.create(o1, o2, o3);
    }

}
