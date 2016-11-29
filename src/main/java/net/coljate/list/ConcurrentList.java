package net.coljate.list;

import net.coljate.collection.ConcurrentCollection;

/**
 *
 * @author ollie
 */
public interface ConcurrentList<T> extends MutableList<T>, ConcurrentCollection<T> {
    
    @Override
    ConcurrentList<T> mutableCopy();

}
