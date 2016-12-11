package net.coljate.set.impl;

import net.coljate.collection.AllCollectionSizeTests;
import net.coljate.collection.ObjectCollectionTest;
import net.coljate.set.MutableSetTests;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedSetTest
        extends ObjectCollectionTest
        implements AllCollectionSizeTests<Object>, MutableSetTests<Object> {

    @Override
    public MutableWrappedSet<Object> create(final Object... elements) {
        return MutableWrappedSet.copyOf(elements);
    }

}
