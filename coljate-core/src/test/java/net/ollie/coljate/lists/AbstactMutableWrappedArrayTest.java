package net.ollie.coljate.lists;

import net.ollie.coljate.lists.MutableWrappedArray;

import java.util.Arrays;

import net.ollie.coljate.AbstractMutableArrayTest;
import net.ollie.coljate.Array;

/**
 *
 * @author Ollie
 */
public class AbstactMutableWrappedArrayTest
        extends AbstractMutableArrayTest<Array.Mutable<Object>> {

    @Override
    protected Array.Mutable<Object> create(final Object... objects) {
        return MutableWrappedArray.copy(Arrays.asList(objects));
    }

}
