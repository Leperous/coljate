package net.coljate.list;

import net.coljate.list.impl.MutableAtomicArray;

/**
 *
 * @author ollie
 */
public interface ConcurrentArray<T>
        extends MutableArray<T>, ConcurrentList<T> {

    @Override
    ConcurrentArray<T> mutableCopy();

    static <T> ConcurrentArray<T> createConcurrentArray(final int length) {
        return MutableAtomicArray.create(length);
    }

}
