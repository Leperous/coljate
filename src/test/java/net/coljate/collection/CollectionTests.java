package net.coljate.collection;

/**
 *
 * @author ollie
 */
public interface CollectionTests<T> {
    
    T createObject();
    
    Collection<T> create(T... elements);

}
