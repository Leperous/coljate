package net.ollie.coljate.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 *
 * @author ollie
 */
public class Iterators {

    private static final Iterator NONE = new Iterator() {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            throw new NoSuchElementException();
        }

    };

    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> none() {
        return NONE;
    }

    public static int count(final Iterator<?> iterator) {
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }

    public static <F, T> Iterator<T> transform(final Iterator<F> iterator, final Function<? super F, ? extends T> transform) {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return transform.apply(iterator.next());
            }

            @Override
            public void remove() {
                iterator.remove();
            }

        };
    }

    public static <T> Iterator<T> of(final T element) {
        return new Iterator<T>() {

            boolean used = false;

            @Override
            public boolean hasNext() {
                return !used;
            }

            @Override
            public T next() {
                used = true;
                return element;
            }

        };
    }

}
