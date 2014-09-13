package net.ollie.coljate.sets;

import net.ollie.coljate.sets.MutableWrappedMultiHashSet;
import net.ollie.coljate.AbstractMutableMultisetTest;
import net.ollie.coljate.MultiSet;

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
