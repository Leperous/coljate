package net.ollie.sc4j.lists;

import java.util.Arrays;

import net.ollie.sc4j.AbstractQueueTest;
import net.ollie.sc4j.Queue;

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
