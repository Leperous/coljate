package net.ollie.coljate.utils;

import java.util.Iterator;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class DelegatedUnmodifiableIterator<T> extends UnmodifiableIterator<T> {

    private final Iterator<? extends T> delegate;

    public DelegatedUnmodifiableIterator(final Iterator<? extends T> delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public boolean hasNext() {
        return delegate.hasNext();
    }

    @Override
    public T next() {
        return delegate.next();
    }

}
