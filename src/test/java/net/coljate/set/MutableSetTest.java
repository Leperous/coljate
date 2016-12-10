package net.coljate.set;

import net.coljate.collection.MutableCollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class MutableSetTest extends MutableCollectionTest {

    @Override
    protected abstract <T> MutableSet<T> create(T... elements);

    @Test
    public void testAdd_Twice() {
        final MutableSet<Object> set = this.create();
        final Object element = this.createObject();
        assertTrue("Should add element", set.add(element));
        assertFalse(set.add(element));
        assertThat(set.count(), is(1));
    }

    @Test
    public void testRemove_Singleton() {
        final Object element = this.createObject();
        final MutableSet<Object> set = this.create(element);
        assertTrue("Should remove element", set.remove(element));
        assertTrue(set.isEmpty());
        assertFalse(set.contains(element));
    }

}
