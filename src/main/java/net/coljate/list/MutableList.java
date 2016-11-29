package net.coljate.list;

import net.coljate.collection.MutableCollection;

/**
 *
 * @author ollie
 */
public interface MutableList<T> extends List<T>, MutableCollection<T> {
    
    void prefix(T element);
    
    void suffix(T element);

}
