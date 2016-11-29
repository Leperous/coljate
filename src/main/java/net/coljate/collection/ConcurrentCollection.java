package net.coljate.collection;

/**
 *
 * @author ollie
 */
public interface ConcurrentCollection<T> extends MutableCollection<T> {

    @Override
    ConcurrentCollection<T> mutableCopy();

}
