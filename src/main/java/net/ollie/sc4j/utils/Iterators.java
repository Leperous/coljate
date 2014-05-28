package net.ollie.sc4j.utils;

import java.util.Iterator;

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

    private static final class ArrayIterator<V>
            implements Iterator<V> {

        private final V[] array;
        private int index;

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

}
