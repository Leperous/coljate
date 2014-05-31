package net.ollie.sc4j.utils;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Supplier;

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

    public static <V> Iterator<V> yield(final Supplier<Boolean> hasNext, final Supplier<? extends V> next) {
        return new YieldingIterator<>(hasNext, next);
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

    private static class YieldingIterator<V>
            implements Iterator<V> {

        private final Supplier<Boolean> hasNext;
        private final Supplier<? extends V> next;

        YieldingIterator(final Supplier<Boolean> hasNext, final Supplier<? extends V> next) {
            this.hasNext = hasNext;
            this.next = next;
        }

        @Override
        public boolean hasNext() {
            return hasNext.get();
        }

        @Override
        public V next() {
            return next.get();
        }

    }

}
