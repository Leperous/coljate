package net.ollie.coljate;

import java.util.Iterator;

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

}
