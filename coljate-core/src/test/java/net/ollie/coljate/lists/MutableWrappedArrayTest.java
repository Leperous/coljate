package net.ollie.coljate.lists;

import java.util.Arrays;


/**
 *
 * @author Ollie
 */
public class MutableWrappedArrayTest
        extends AbstractMutableArrayTest<Array.Mutable<Object>> {

    @Override
    protected Array.Mutable<Object> create(final Object... objects) {
        return MutableWrappedArray.copy(Arrays.asList(objects));
    }

}
