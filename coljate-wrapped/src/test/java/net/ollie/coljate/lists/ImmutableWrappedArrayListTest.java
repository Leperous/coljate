package net.ollie.coljate.lists;

import net.ollie.coljate.lists.ImmutableWrappedArray;

import java.util.Arrays;

import net.ollie.coljate.AbstractImmutableArrayTest;
import net.ollie.coljate.Array;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedArrayListTest
        extends AbstractImmutableArrayTest {

    @Override
    protected Array.Immutable<Object> create(final Object... objects) {
        return ImmutableWrappedArray.copy(Arrays.asList(objects));
    }

}
