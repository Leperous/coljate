package net.ollie.sc4j.lists;

import java.util.Arrays;

import net.ollie.sc4j.AbstractMutableArrayTest;
import net.ollie.sc4j.Array;

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
