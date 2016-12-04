package net.coljate.collection.impl;

import net.coljate.collection.ImmutableCollectionTest;

import org.junit.Assume;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptyCollectionTest extends ImmutableCollectionTest {

    @Override
    protected <T> EmptyCollection<T> create(final T... elements) {
        Assume.assumeTrue(elements.length == 0);
        return EmptyCollection.instance();
    }

}
