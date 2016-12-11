package net.coljate.set.impl;

import org.junit.Assume;

import net.coljate.collection.ObjectCollectionTest;
import net.coljate.collection.SingletonCollectionTests;
import net.coljate.set.ImmutableSetTests;

/**
 *
 * @author ollie
 */
public class SingletonSetTest
        extends ObjectCollectionTest
        implements ImmutableSetTests<Object>, SingletonCollectionTests<Object> {

    @Override
    public SingletonSet<Object> createSingleton(final Object element) {
        return SingletonSet.of(element);
    }

    @Override
    public SingletonSet<Object> create(final Object... elements) {
        Assume.assumeTrue(elements.length == 1);
        return this.createSingleton(elements[0]);
    }

}
