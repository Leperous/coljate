package net.ollie.sc4j;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class AbstractQueueTest<C extends Queue.Mutable<Object>>
        extends AbstractCollectionTest<C> {

    @Test
    public void shouldOfferPeekPoll() {
        final Object o1 = new Object();
        final C queue = this.create(o1);
        assertContains(queue, o1);
        assertThat(queue.peek(), is(o1));
    }

    @Test
    public void shouldOfferPeekPollFromEmpty() {
        final C queue = this.create();
        final Object o1 = new Object();
        assertTrue(queue.offer(o1));
        assertThat(queue.peek(), is(o1));
        final Object o2 = new Object();
        assertTrue(queue.offer(o2));
        assertThat(queue.peek(), is(o1));
        assertThat(queue.poll(), is(o1));
        assertThat(queue.peek(), is(o2));
        assertThat(queue.poll(), is(o2));
        assertNull(queue.peek());
    }

    @Test
    public void shouldTail() {

        final Object o1 = new Object(), o2 = new Object(), o3 = new Object();
        final C queue = this.create(o1, o2, o3);

        final Queue<Object> tail1 = queue.tail();
        assertFalse(tail1.contains(o1));
        assertTrue(tail1.contains(o2));
        assertTrue(tail1.contains(o3));

        final Queue<Object> tail2 = tail1.tail();
        assertFalse(tail2.contains(o1));
        assertFalse(tail2.contains(o2));
        assertTrue(tail2.contains(o3));

        final Queue<Object> tail3 = tail2.tail();
        assertFalse(tail3.contains(o1));
        assertFalse(tail3.contains(o2));
        assertFalse(tail3.contains(o3));

        final Queue<Object> tail4 = tail3.tail();
        assertFalse(tail4.contains(o1));
        assertFalse(tail4.contains(o2));
        assertFalse(tail4.contains(o3));

    }

    @Test
    public void shouldDrain() {

        final Object o1 = new Object(), o2 = new Object(), o3 = new Object();
        final C queue = this.create(o1, o2, o3);

        final Sequence<Object> drained = queue.drain();
        assertContainsNothing(queue);
        assertTrue(drained.contains(o1));
        assertTrue(drained.contains(o2));
        assertTrue(drained.contains(o3));

    }

}
