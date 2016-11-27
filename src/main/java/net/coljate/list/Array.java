package net.coljate.list;

import java.util.RandomAccess;

/**
 *
 * @author ollie
 */
public interface Array<T> extends List<T>, RandomAccess {

    /**
     *
     * @param index
     * @return
     */
    T get(int index);

    /**
     * @return the length of this array. It will be equal to or greater than the
     * {@link #count}.
     */
    int length();

    @Override
    ImmutableArray<T> immutableCopy();

}
