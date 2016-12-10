package net.coljate.list;

import net.coljate.collection.MutableCollectionTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class QueueTest extends MutableCollectionTest {

    @Override
    protected abstract <T> Queue<T> create(T... elements);

    @Test
    public void testPoll_Empty() {
        final Queue<Object> queue = this.create();
        assertNull(queue.poll());
    }

    @Test
    public void testPoll_Singleton() {
        final Object element = this.createObject();
        final Queue<Object> queue = this.create(element);
        assertThat(queue.poll(), is(element));
        assertNull(queue.poll());
    }

}
