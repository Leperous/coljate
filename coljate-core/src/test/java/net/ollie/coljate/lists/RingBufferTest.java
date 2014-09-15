package net.ollie.coljate.lists;

import net.ollie.coljate.lists.RingBuffer;

import java.util.Arrays;

/**
 * @author Ollie
 */
public class RingBufferTest
        extends AbstractQueueTest<RingBuffer<Object>> {

    @Override
    protected RingBuffer<Object> create(final Object... objects) {
        return RingBuffer.copy(Arrays.asList(objects));
    }

    @Override
    public void shouldOfferPeekPollFromEmpty() {
        //Cannot offer to create
    }

}
