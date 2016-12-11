package net.coljate.list;

import net.coljate.collection.ImmutableCollectionTest;

/**
 *
 * @author ollie
 */
public interface ImmutableListTest<T> extends ListTest<T>, ImmutableCollectionTest<T> {

    @Override
    ImmutableList<T> create(java.util.List<T> elements);

}
