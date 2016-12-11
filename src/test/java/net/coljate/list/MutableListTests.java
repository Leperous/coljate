package net.coljate.list;

import net.coljate.collection.MutableCollectionTests;

/**
 *
 * @author Ollie
 */
public interface MutableListTests<T> extends ListTests<T>, MutableCollectionTests<T> {

    @Override
    MutableList<T> create(T... elements);

}
