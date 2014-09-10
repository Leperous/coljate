package net.ollie.sc4j.lists;

import net.ollie.sc4j.AbstractMutableArrayTest;
import net.ollie.sc4j.Array;
import net.ollie.sc4j.Array.Mutable;

/**
 *
 * @author Ollie
 */
public class MutableArrayBackedArrayTest
        extends AbstractMutableArrayTest<Array.Mutable<Object>> {

    @Override
    protected Mutable<Object> create(final Object... objects) {
        return AbstactMutableWrappedArray.view(objects);
    }

}
