package net.coljate;

import java.util.Iterator;

/**
 *
 * @author ollie
 */
public abstract class UnmodifiableIterator<T> implements Iterator<T> {

    @Override
    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException("Unmodifiable");
    }

}
