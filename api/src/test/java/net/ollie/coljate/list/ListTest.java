package net.ollie.coljate.list;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import net.ollie.coljate.CollectionTest;
import net.ollie.coljate.ObjectCollectionBuilder;

/**
 *
 * @author Ollie
 */
public abstract class ListTest extends CollectionTest<Object> implements ObjectCollectionBuilder {

    @Override
    public abstract List<Object> createFrom(Object... objects);

    @Override
    public List<Object> create(Object first, Object second) {
        return this.createFrom(first, second);
    }

    @Test
    public void testDual_Tail() {
        final Object first = new Object(), second = new Object();
        final List<Object> dual = this.create(first, second);
        assertThat(dual.head(), is(first));
        assertThat(dual.tail(), is(this.create(second)));
    }

}
