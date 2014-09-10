package net.ollie.sc4j.lists;

import net.ollie.sc4j.AbstractMutableArrayTest;
import net.ollie.sc4j.Array;

/**
 *
 * @author Ollie
 */
public class ConcurrentWrappedArrayTest
        extends AbstractMutableArrayTest<Array.Mutable<Object>> {

    @Override
    protected Array.Mutable<Object> create(final Object... objects) {
        return ConcurrentWrappedArray.copy(objects);
    }

}
