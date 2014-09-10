package net.ollie.coljate.utils.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Ollie
 */
public interface UnmodifiableIterator<V> extends Iterator<V> {

    static <V> UnmodifiableIterator<V> of() {
        return EmptyIterator.INSTANCE;
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

    @SuppressWarnings("unchecked")
    static <V> UnmodifiableIterator<V> of(final Iterator<? extends V> iterator) {
        if (iterator instanceof UnmodifiableIterator) {
            return (UnmodifiableIterator<V>) iterator;
        }
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

}
