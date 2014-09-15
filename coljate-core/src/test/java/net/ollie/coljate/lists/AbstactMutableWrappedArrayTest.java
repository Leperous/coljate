package net.ollie.coljate.lists;

import net.ollie.coljate.lists.MutableWrappedArray;

import java.util.Arrays;


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
