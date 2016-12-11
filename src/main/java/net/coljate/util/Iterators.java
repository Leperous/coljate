package net.coljate.util;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Ollie
 */
public class Iterators {

    public static <F, T> Iterator<T> transform(final Iterator<F> from, final Function<? super F, ? extends T> transform) {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return from.hasNext();
            }

            @Override
            public T next() {
                return transform.apply(from.next());
            }

            @Override
            public void remove() {
                from.remove();
            }

        };
    }

    public static <T> Iterator<T> filter(final Iterator<? extends T> iterator, final Predicate<? super T> predicate) {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                throw new UnsupportedOperationException(); //TODO
            }

            @Override
            public T next() {
                throw new UnsupportedOperationException(); //TODO
            }

            @Override
            public void remove() {
                iterator.remove();
            }

        };
    }

    public static int count(final Iterator<?> iterator) {
        int count = 0;
        while (iterator.hasNext()) {
            count++;
        }
        return count;
    }

}
