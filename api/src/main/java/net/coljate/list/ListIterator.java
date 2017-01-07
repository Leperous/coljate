package net.coljate.list;

import java.util.Iterator;

/**
 *
 * @author ollie
 * @see java.util.ListIterator
 */
public interface ListIterator<T> extends Iterator<T> {

    boolean hasPrevious();

    T previous();

    static <T> ListIterator<T> wrap(final java.util.ListIterator<? extends T> iterator) {
        throw new UnsupportedOperationException(); //TODO
    }

}
