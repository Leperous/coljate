package net.coljate.list.impl;

import java.util.Comparator;

import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.collection.ObjectCollectionTest;
import net.coljate.collection.SortedCollection.SortingAlgorithm;
import net.coljate.list.SortedArray;
import net.coljate.list.SortedArrayTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableSortedArrayTest
        extends ObjectCollectionTest
        implements SortedArrayTest<Object>, AllCollectionSizeTest<Object> {

    @Override
    public SortedArray<Object> create(final java.util.List<Object> elements) {
        return SortedArrayTest.super.create(elements);
    }

    @Override
    public SortedArray<Object> create(final java.util.List<Object> elements, final Comparator<Object> comparator) {
        return ImmutableSortedArray.sort(elements, comparator, SortingAlgorithm.DEFAULT);
    }

}
