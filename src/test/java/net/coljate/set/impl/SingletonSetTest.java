package net.coljate.set.impl;

import net.coljate.AbstractObjectTest;
import net.coljate.collection.SingletonCollectionTests;

/**
 *
 * @author ollie
 */
public class SingletonSetTest
        extends AbstractObjectTest
        implements SingletonCollectionTests<Object> {

    @Override
    public SingletonSet<Object> createSingleton(final Object element) {
        return SingletonSet.of(element);
    }

}
