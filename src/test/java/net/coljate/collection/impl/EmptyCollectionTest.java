package net.coljate.collection.impl;

import net.coljate.collection.EmptyCollectionTests;
import net.coljate.collection.ObjectCollectionTest;

/**
 *
 * @author ollie
 */
public class EmptyCollectionTest
        extends ObjectCollectionTest
        implements EmptyCollectionTests<Object> {

    @Override
    public EmptyCollection<Object> createEmpty() {
        return EmptyCollection.instance();
    }

    @Override
    public Object createObject() {
        return new Object();
    }

}
