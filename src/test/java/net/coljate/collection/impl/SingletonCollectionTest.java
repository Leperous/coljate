package net.coljate.collection.impl;

import net.coljate.collection.ObjectCollectionTest;
import net.coljate.collection.SingletonCollectionTests;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SingletonCollectionTest
        extends ObjectCollectionTest
        implements SingletonCollectionTests<Object> {

    @Override
    public SingletonCollection<Object> createSingleton(final Object element) {
        return SingletonCollection.of(element);
    }

}
