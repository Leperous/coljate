package net.coljate.set;

import net.coljate.collection.ImmutableCollectionTest;

/**
 *
 * @author ollie
 */
public interface ImmutableSetTest<T> extends SetTest<T>, ImmutableCollectionTest<T> {

    @Override
    ImmutableSet<T> createTestCollection();

    interface OneElementTests<T> extends ImmutableSetTest<T>, SetTest.OneElementTests<T> {

    }

}
