package net.coljate.list.impl;

import net.coljate.collection.ObjectCollectionTest;
import net.coljate.collection.AllCollectionSizeTests;
import net.coljate.list.MutableArrayTests;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableNativeArrayTest
        extends ObjectCollectionTest
        implements MutableArrayTests<Object>, AllCollectionSizeTests<Object> {

    @Override
    public MutableNativeArray<Object> create(final Object... elements) {
        return MutableNativeArray.viewOf(elements);
    }

}
