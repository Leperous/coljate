package net.coljate.set.impl;

import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.collection.ObjectCollectionTest;
import net.coljate.set.ConcurrentSet;
import net.coljate.set.ConcurrentSetTest;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ConcurrentWrappedSetTest
        extends ObjectCollectionTest
        implements AllCollectionSizeTest<Object>, ConcurrentSetTest<Object> {

    @Override
    public ConcurrentWrappedSet<Object> create(final java.util.List<Object> elements) {
        return ConcurrentWrappedSet.copyIntoHashSet(elements);
    }

    @Override
    public ConcurrentSet<Object> create(final Object element) {
        return ConcurrentSetTest.super.create(element);
    }

}
