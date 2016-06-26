package net.ollie.coljate.list;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import net.ollie.coljate.CollectionTest;

/**
 *
 * @author Ollie
 */
public abstract class ListTest extends CollectionTest<Object> {

    @Override
    protected List<Object> create() {
        return createFrom();
    }

    @Override
    protected List<Object> create(Object singleton) {
        return createFrom(singleton);
    }

    @Override
    protected List<Object> create(Object first, Object second) {
        return createFrom(first, second);
    }

    protected abstract List<Object> createFrom(Object... objects);

    @Override
    protected Object randomValue() {
        return new Object();
    }

    @Test
    public void testDual_Tail() {
        final Object first = new Object(), second = new Object();
        final List<Object> dual = this.create(first, second);
        assertThat(dual.head(), is(first));
        assertThat(dual.tail(), is(this.create(second)));
    }

}
