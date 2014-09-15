package net.ollie.coljate.sets;

import net.ollie.coljate.sets.MutableWrappedMultiHashSet;

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
