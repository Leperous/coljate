package net.coljate.list;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class MutableArrayTest extends MutableListTest {

    @Override
    protected abstract <T> MutableArray<T> create(T... elements);
    
    @Test
    public void testElementIndex() {
        final Object o1 = new Object();
        final Object o2 = new Object();
        final MutableArray<Object> array = this.create(o1, o2);
        assertThat(array.get(0), is(o1));
        assertThat(array.get(1), is(o2));
    }

}
