package net.coljate.list;

/**
 *
 * @author ollie
 */
public interface ConcurrentArray<T> extends MutableArray<T>, ConcurrentList<T> {

    @Override
    ConcurrentArray<T> mutableCopy();

}
