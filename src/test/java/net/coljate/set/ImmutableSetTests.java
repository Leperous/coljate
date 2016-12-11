package net.coljate.set;

import net.coljate.collection.ImmutableCollectionTests;

/**
 *
 * @author ollie
 */
public interface ImmutableSetTests<T> extends SetTests<T>, ImmutableCollectionTests<T> {
    
    @Override
    ImmutableSet<T> create(T... elements);

}
