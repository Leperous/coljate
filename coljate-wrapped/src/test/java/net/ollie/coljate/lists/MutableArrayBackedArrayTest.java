package net.ollie.coljate.lists;

import net.ollie.coljate.lists.MutableWrappedArray;
import net.ollie.coljate.AbstractMutableArrayTest;
import net.ollie.coljate.Array;
import net.ollie.coljate.Array.Mutable;

/**
 *
 * @author Ollie
 */
public class MutableArrayBackedArrayTest
        extends AbstractMutableArrayTest<Array.Mutable<Object>> {

    @Override
    protected Mutable<Object> create(final Object... objects) {
        return MutableWrappedArray.view(objects);
    }

}
