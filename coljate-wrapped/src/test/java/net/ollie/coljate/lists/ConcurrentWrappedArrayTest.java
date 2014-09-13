package net.ollie.coljate.lists;

import net.ollie.coljate.lists.ConcurrentWrappedArray;
import net.ollie.coljate.AbstractMutableArrayTest;
import net.ollie.coljate.Array;

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