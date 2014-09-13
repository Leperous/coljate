package net.ollie.coljate.lists;

import net.ollie.coljate.lists.MutableWrappedLinkedQueue;

import java.util.Arrays;

import net.ollie.coljate.AbstractQueueTest;
import net.ollie.coljate.Queue;

/**
 *
 * @author Ollie
 */
public class MutableWrappedLinkedQueueTest
        extends AbstractQueueTest<Queue.Mutable<Object>> {

    @Override
    protected Queue.Mutable<Object> create(final Object... objects) {
        return MutableWrappedLinkedQueue.copy(Arrays.asList(objects));
    }

}
