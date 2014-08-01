package net.ollie.sc4j.utils;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Function;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public final class Iterators {

    private Iterators() {
    }

    public static <V> Iterator<V> of(final V[] array) {
        return new ArrayIterator<>(array);
    }

    public static <F, T> Iterator<T> transform(final Iterable<F> iterable, final Function<? super F, ? extends T> function) {
        return new TransformedIterator<>(iterable.iterator(), function);
    }

    public static <V> Iterator<V> of(final AtomicReferenceArray<? extends V> array) {
        return new AtomicReferenceArrayIterator<>(array);
    }

    public static <V> UnmodifiableIterator<V> empty() {
        return UnmodifiableIterator.of();
    }

    public static <V> UnmodifiableIterator<V> singleton(@Nonnull final V element) {
        return UnmodifiableIterator.of(element);
    }

    @SuppressWarnings("unchecked")
    public static <V> UnmodifiableIterator<V> unmodifiable(final Iterator<? extends V> iterator) {
        return iterator instanceof UnmodifiableIterator
                ? (UnmodifiableIterator<V>) iterator
                : UnmodifiableIterator.<V>of(iterator);
    }

    private static final class ArrayIterator<V>
            implements Iterator<V> {

        final V[] array;
        int index;

        ArrayIterator(final V[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public V next() {
            return array[index++];
        }

    }

    private static final class TransformedIterator<F, T>
            implements Iterator<T> {

        final Iterator<F> iterator;
        final Function<? super F, ? extends T> function;

        TransformedIterator(final Iterator<F> iterator, final Function<? super F, ? extends T> function) {
            this.iterator = iterator;
            this.function = function;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return function.apply(iterator.next());
        }

        @Override
        public void remove() {
            iterator.remove();
        }

    }

    private static final class AtomicReferenceArrayIterator<V>
            implements Iterator<V> {

        private final AtomicReferenceArray<? extends V> array;
        int index;

        AtomicReferenceArrayIterator(final AtomicReferenceArray<? extends V> array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return index < array.length();
        }

        @Override
        public V next() {
            return array.get(index++);
        }

    }

}
