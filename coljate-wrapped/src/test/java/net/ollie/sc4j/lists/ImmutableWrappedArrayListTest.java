package net.ollie.sc4j.lists;

import java.util.Arrays;

import net.ollie.sc4j.AbstractImmutableArrayTest;
import net.ollie.sc4j.Array;

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
