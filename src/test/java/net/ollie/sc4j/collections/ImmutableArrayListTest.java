package net.ollie.sc4j.collections;

import java.util.Arrays;

import net.ollie.sc4j.AbstractImmutableArrayTest;
import net.ollie.sc4j.Array;

/**
 *
 * @author Ollie
 */
public class ImmutableArrayListTest
        extends AbstractImmutableArrayTest {

    @Override
    protected Array.Immutable<Object> create(final Object... objects) {
        return ImmutableArray.copy(Arrays.asList(objects));
    }

}
