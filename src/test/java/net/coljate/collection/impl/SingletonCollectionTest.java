package net.coljate.collection.impl;

import net.coljate.collection.ImmutableCollectionTest;

import org.junit.Assume;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SingletonCollectionTest extends ImmutableCollectionTest {

    @Override
    protected <T> SingletonCollection<T> create(T... elements) {
        Assume.assumeTrue(elements.length == 1);
        return SingletonCollection.of(elements[0]);
    }

}
