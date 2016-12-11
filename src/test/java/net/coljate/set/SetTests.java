package net.coljate.set;

import net.coljate.collection.CollectionTests;

/**
 *
 * @author Ollie
 */
public interface SetTests<T> extends CollectionTests<T> {
    
    @Override
    Set<T> create(T... elements);

}
