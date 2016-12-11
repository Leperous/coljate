package net.coljate.list;

/**
 *
 * @author ollie
 */
public interface SortedArray<T> extends SortedList<T>, Array<T> {

    @Override
    default T last() {
        return Array.super.last();
    }

}
