package net.coljate.list;

import net.coljate.collection.ImmutableCollectionTests;

/**
 *
 * @author ollie
 */
public interface ImmutableListTests<T> extends ListTests<T>, ImmutableCollectionTests<T> {

    @Override
    ImmutableList<T> create(T... elements);

}
