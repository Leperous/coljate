package net.coljate.list;

import net.coljate.collection.CollectionTests;

/**
 *
 * @author Ollie
 */
public interface ListTests<T> extends CollectionTests<T> {

    @Override
    List<T> create(T... elements);

}
