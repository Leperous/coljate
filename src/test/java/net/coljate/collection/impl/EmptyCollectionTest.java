package net.coljate.collection.impl;

import net.coljate.collection.ObjectCollectionTest;

/**
 *
 * @author ollie
 */
public class EmptyCollectionTest
        extends ObjectCollectionTest
        implements net.coljate.collection.EmptyCollectionTest<Object> {

    @Override
    public EmptyCollection<Object> createEmpty() {
        return EmptyCollection.instance();
    }

    @Override
    public Object createObject() {
        return new Object();
    }

}
