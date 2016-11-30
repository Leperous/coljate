package net.coljate.list.impl;

import net.coljate.list.Queue;
import net.coljate.list.QueueTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class WrappedQueueTest extends QueueTest {

    @Override
    protected <T> Queue<T> create(final T... elements) {
        return WrappedQueue.copyOf(elements);
    }

}
