package net.ollie.sc4j.collections;

import java.util.Arrays;

import net.ollie.sc4j.AbstractMutableArrayTest;
import net.ollie.sc4j.Array;

/**
 *
 * @author Ollie
 */
public class MutableListBackedArrayTest
        extends AbstractMutableArrayTest {

    @Override
    protected Array.Mutable<Object> create(final Object... objects) {
        return MutableArray.copy(Arrays.asList(objects));
    }

}
