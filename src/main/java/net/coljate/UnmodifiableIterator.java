package net.coljate;

import java.util.Iterator;

/**
 *
 * @author ollie
 */
public interface UnmodifiableIterator<T> extends Iterator<T> {

    static <T> UnmodifiableIterator<T> wrap(final Iterator<? extends T> iterator) {
        return iterator instanceof UnmodifiableIterator
                ? (UnmodifiableIterator) iterator
                : new DelegatedUnmodifiableIterator<>(iterator);
    }

    @Override
    @Deprecated
    default void remove() {
        throw new UnsupportedOperationException("Unmodifiable");
    }

    class DelegatedUnmodifiableIterator<T> implements UnmodifiableIterator<T> {

        private final Iterator<? extends T> delegate;

        DelegatedUnmodifiableIterator(Iterator<? extends T> delegate) {
            this.delegate = delegate;
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

}
