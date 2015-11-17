package net.ollie.coljate.utils;

import net.ollie.coljate.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class UnmodifiableArrayIterator<T> extends UnmodifiableIterator<T> {

    private final T[] array;
    private int index;

    public UnmodifiableArrayIterator(final T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public T next() {
        return array[index++];
    }

}
