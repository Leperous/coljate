package net.coljate.list;

/**
 *
 * @author Ollie
 */
public interface MutableArrayTests<T> extends ArrayTests<T>, MutableListTests<T> {

    @Override
    MutableArray<T> create(T... elements);

}
