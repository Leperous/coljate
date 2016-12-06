package net.coljate.list;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class ImmutableArrayTest extends ImmutableListTest {

    protected abstract <T> ImmutableArray<T> create(T... elements);

    @Test(expected = NoSuchElementException.class)
    public void testGet_Empty() {
        final ImmutableArray<Object> array = this.create();
        array.get(0);
    }

}
