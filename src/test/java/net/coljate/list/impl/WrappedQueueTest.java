package net.coljate.list.impl;

import net.coljate.list.QueueTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class WrappedQueueTest extends QueueTest {

    @Override
    protected <T> WrappedQueue<T> create(final T... elements) {
        return WrappedQueue.copyOf(elements);
    }

}
