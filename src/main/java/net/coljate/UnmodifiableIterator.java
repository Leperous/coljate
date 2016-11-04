package net.coljate;

import java.util.Iterator;

/**
 *
 * @author ollie
 */
public interface UnmodifiableIterator<T> extends Iterator<T> {

    @Override
    @Deprecated
    default void remove() {
        throw new UnsupportedOperationException("Unmodifiable");
    }

}
