package net.coljate.list;

/**
 *
 * @author Ollie
 */
public interface MutableArrayTest<T> extends ArrayTest<T>, MutableListTest<T> {

    @Override
    MutableArray<T> create(T... elements);

}
