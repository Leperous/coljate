package net.coljate.set.impl;

import net.coljate.AbstractObjectTest;
import net.coljate.collection.SingletonCollectionTests;
import net.coljate.set.ImmutableSet;
import net.coljate.set.ImmutableSetTests;

import org.junit.Assume;

/**
 *
 * @author ollie
 */
public class SingletonSetTest
        extends AbstractObjectTest
        implements SingletonCollectionTests<Object>, ImmutableSetTests<Object> {

    @Override
    public SingletonSet<Object> createSingleton(final Object element) {
        return SingletonSet.of(element);
    }

    @Override
    public ImmutableSet<Object> create(final Object... elements) {
        Assume.assumeTrue(elements.length == 1);
        return this.createSingleton(elements[0]);
    }

}
