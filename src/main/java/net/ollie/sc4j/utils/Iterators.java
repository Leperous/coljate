package net.ollie.sc4j.utils;

import java.util.Iterator;
import java.util.function.Function;

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

    public static <F, T> Iterator<T> transform(final Iterator<F> iterator, final Function<? super F, ? extends T> function) {
        return new TransformedIterator<>(iterator, function);
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

}
