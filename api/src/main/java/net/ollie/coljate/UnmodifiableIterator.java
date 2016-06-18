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
    public static <T> UnmodifiableIterator<T> none() {
        return NONE;
    }

    @SuppressWarnings("rawtypes")
    private static final UnmodifiableIterator NONE = new UnmodifiableIterator() {

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
