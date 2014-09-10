package net.ollie.sc4j.sets;

import net.ollie.sc4j.AbstractMutableMultisetTest;
import net.ollie.sc4j.MultiSet;

/**
 *
 * @author Ollie
 */
public class MutableWrappedMultiHashSetTest
        extends AbstractMutableMultisetTest<MultiSet.Mutable<Object>> {

    @Override
    protected MultiSet.Mutable<Object> create(final Object... objects) {
        return MutableWrappedMultiHashSet.copy(objects);
    }

}
