package net.coljate.list;

import net.coljate.collection.ImmutableCollectionTest;

/**
 *
 * @author ollie
 */
public abstract class ImmutableListTest extends ImmutableCollectionTest {

    protected abstract <T> ImmutableList<T> create(T... Elements);

}
