package net.ollie.coljate;

import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class UnmodifiableGuavaIterator<V> implements UnmodifiableIterator<V> {

    public static <V> UnmodifiableIterator<V> of(final com.google.common.collect.UnmodifiableIterator<? extends V> delegate) {
        return delegate.hasNext()
                ? new UnmodifiableGuavaIterator<>(delegate)
                : UnmodifiableIterator.of();
    }

    private final com.google.common.collect.UnmodifiableIterator<? extends V> delegate;

    protected UnmodifiableGuavaIterator(final com.google.common.collect.UnmodifiableIterator<? extends V> delegate) {
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
