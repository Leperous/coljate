package net.coljate.list.impl;

import net.coljate.collection.ObjectCollectionTest;
import net.coljate.list.MutableArrayTest;
import net.coljate.collection.AllCollectionSizeTest;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableNativeArrayTest
        extends ObjectCollectionTest
        implements MutableArrayTest<Object>, AllCollectionSizeTest<Object> {

    @Override
    public MutableNativeArray<Object> create(final Object... elements) {
        return MutableNativeArray.viewOf(elements);
    }

}
