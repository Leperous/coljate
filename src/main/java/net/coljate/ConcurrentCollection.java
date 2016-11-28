package net.coljate;

/**
 *
 * @author ollie
 */
public interface ConcurrentCollection<T> extends MutableCollection<T> {

    @Override
    ConcurrentCollection<T> mutableCopy();

}
