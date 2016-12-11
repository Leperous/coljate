package net.coljate.collection.impl;

import net.coljate.collection.ObjectCollectionTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SingletonCollectionTest
        extends ObjectCollectionTest
        implements net.coljate.collection.SingletonCollectionTest<Object> {

    @Override
    public SingletonCollection<Object> createSingleton(final Object element) {
        return SingletonCollection.of(element);
    }

}
