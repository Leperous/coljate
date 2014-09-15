package net.ollie.coljate.lists;

import java.util.Arrays;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedArrayTest
        extends AbstractImmutableArrayTest {

    @Override
    protected Array.Immutable<Object> create(final Object... objects) {
        return ImmutableWrappedArray.copy(Arrays.asList(objects));
    }

}
