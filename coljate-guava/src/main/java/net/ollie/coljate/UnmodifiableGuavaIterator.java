package net.ollie.coljate;

import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class UnmodifiableGuavaIterator<V> implements UnmodifiableIterator<V> {

    private final com.google.common.collect.UnmodifiableIterator<? extends V> delegate;

    public UnmodifiableGuavaIterator(final com.google.common.collect.UnmodifiableIterator<? extends V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean hasNext() {
        return delegate.hasNext();
    }

    @Override
    public V next() {
        return delegate.next();
    }

}
