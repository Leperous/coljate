package net.ollie.sc4j.collections;

import net.ollie.sc4j.AbstractMutableArrayTest;
import net.ollie.sc4j.Array.Mutable;

/**
 *
 * @author Ollie
 */
public class MutableArrayBackedArrayTest
        extends AbstractMutableArrayTest {

    @Override
    protected Mutable<Object> create(final Object... objects) {
        return MutableArray.view(objects);
    }

}
