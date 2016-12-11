package net.coljate.set.impl;

import net.coljate.collection.ObjectCollectionTest;
import net.coljate.set.MutableSetTest;
import net.coljate.collection.AllCollectionSizeTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedSetTest
        extends ObjectCollectionTest
        implements AllCollectionSizeTest<Object>, MutableSetTest<Object> {

    @Override
    public MutableWrappedSet<Object> create(final Object... elements) {
        return MutableWrappedSet.copyOf(elements);
    }

}
