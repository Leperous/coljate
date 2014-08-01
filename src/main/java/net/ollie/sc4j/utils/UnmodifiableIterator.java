package net.ollie.sc4j.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Ollie
 */
public interface UnmodifiableIterator<V> extends Iterator<V> {

    static <V> UnmodifiableIterator<V> of() {
        return Empty.INSTANCE;
    }

    static <V> UnmodifiableIterator<V> of(final V element) {
        return new UnmodifiableIterator<V>() {

            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public V next() {
                if (!hasNext) {
                    throw new NoSuchElementException();
                }
                hasNext = false;
                return element;
            }

        };
    }

    static <V> UnmodifiableIterator<V> of(final Iterator<? extends V> iterator) {
        return new UnmodifiableIterator<V>() {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public V next() {
                return iterator.next();
            }

        };
    }

    final class Empty<V> implements UnmodifiableIterator<V> {

        static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public V next() {
            throw new NoSuchElementException();
        }

    }

}
