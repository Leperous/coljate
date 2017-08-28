package net.coljate.list;

import java.util.Iterator;
import java.util.Objects;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 * @see java.util.ListIterator
 */
public interface ListIterator<T> extends Iterator<T> {

    boolean hasPrevious();

    T previous();

    static <T> ListIterator<T> wrap(final java.util.ListIterator<? extends T> iterator) {
        return new WrappedListIterator<>(iterator);
    }

    class WrappedListIterator<T> implements ListIterator<T> {

        private final java.util.ListIterator<? extends T> iterator;

        public WrappedListIterator(@Nonnull final java.util.ListIterator<? extends T> iterator) {
            this.iterator = Objects.requireNonNull(iterator);
        }

        @Override
        public boolean hasPrevious() {
            return iterator.hasPrevious();
        }

        @Override
        public T previous() {
            return iterator.previous();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return iterator.next();
        }

    }

}
