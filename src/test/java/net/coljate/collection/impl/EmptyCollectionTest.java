package net.coljate.collection.impl;

import net.coljate.AbstractTest;
import net.coljate.collection.EmptyCollectionTests;

/**
 *
 * @author ollie
 */
public class EmptyCollectionTest
        extends AbstractTest
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
