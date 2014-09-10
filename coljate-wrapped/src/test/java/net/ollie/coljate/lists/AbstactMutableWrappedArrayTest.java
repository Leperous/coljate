package net.ollie.coljate.lists;

import net.ollie.coljate.lists.AbstactMutableWrappedArray;

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
        return AbstactMutableWrappedArray.copy(Arrays.asList(objects));
    }

}
