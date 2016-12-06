package net.coljate.list;

import net.coljate.collection.ImmutableCollectionTest;
import net.coljate.list.ImmutableListIterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public abstract class ImmutableListTest extends ImmutableCollectionTest {

    protected abstract <T> ImmutableList<T> create(T... Elements);
    
    @Test
    public void testListIterator_Empty() {
        final ImmutableList<Object> list = this.create();
        final ImmutableListIterator<Object> iterator = list.iterator();
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
    }
    
    @Test
    public void testListIterator_Singleton() {
        final Object element = new Object();
        final ImmutableList<Object> list = this.create(element);
        final ImmutableListIterator<Object> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertThat(iterator.next(), is(element));
        assertFalse(iterator.hasNext());
        assertTrue(iterator.hasPrevious());
    }

}
