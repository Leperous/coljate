package net.coljate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class MutableCollectionTest {

    protected abstract <T> MutableCollection<T> create(T... Elements);

    @Test
    public void testCount_Empty() {
        final MutableCollection<Object> empty = this.create();
        assertThat(empty.count(), is(0));
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testEquality_Empty() {
        final MutableCollection<Object> e1 = this.create();
        final MutableCollection<Object> e2 = this.create();
        assertFalse(e1 == e2);
        assertTrue(e1.equals(e2));
        assertTrue(e2.equals(e1));
    }

    @Test
    public void testCount_Singleton() {
        final MutableCollection<Object> collection = this.create(new Object());
        assertFalse(collection.isEmpty());
        assertThat(collection.count(), is(1));
    }

}
