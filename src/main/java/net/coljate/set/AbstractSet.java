package net.coljate.set;

import net.coljate.collection.AbstractCollection;

/**
 *
 * @author ollie
 */
public abstract class AbstractSet<T>
        extends AbstractCollection<T>
        implements Set<T> {
    
    @Override
    public abstract boolean contains(Object object);

}
