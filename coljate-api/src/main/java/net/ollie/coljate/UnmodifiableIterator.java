package net.ollie.coljate;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Ollie
 */
public abstract class UnmodifiableIterator<T> implements Iterator<T> {

    @Override
    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    public static <T> UnmodifiableIterator<T> empty() {
        return EMPTY;
    }

    @SuppressWarnings("rawtypes")
    private static final UnmodifiableIterator EMPTY = new UnmodifiableIterator() {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            throw new NoSuchElementException("Empty iterator!");
        }

    };

}
