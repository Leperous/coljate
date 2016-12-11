package net.coljate.set.impl;

import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.collection.ObjectCollectionTest;
import net.coljate.set.MutableSet;
import net.coljate.set.MutableSetTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedSetTest
        extends ObjectCollectionTest
        implements AllCollectionSizeTest<Object>, MutableSetTest<Object> {

    @Override
    public MutableWrappedSet<Object> create(final java.util.List<Object> elements) {
        return MutableWrappedSet.copyIntoHashSet(elements);
    }

    @Override
    public MutableSet<Object> create(final Object element) {
        return MutableSetTest.super.create(element);
    }

}
