package net.coljate.set.impl;

import org.junit.Assume;

import net.coljate.collection.ObjectCollectionTest;
import net.coljate.set.ImmutableSetTest;
import net.coljate.collection.SingletonCollectionTest;

/**
 *
 * @author ollie
 */
public class SingletonSetTest
        extends ObjectCollectionTest
        implements ImmutableSetTest<Object>, SingletonCollectionTest<Object> {

    @Override
    public SingletonSet<Object> create(final Object element) {
        return SingletonSet.of(element);
    }

    @Override
    public SingletonSet<Object> create(final java.util.List<Object> elements) {
        Assume.assumeTrue(elements.size() == 1);
        return this.create(elements.get(0));
    }

}
