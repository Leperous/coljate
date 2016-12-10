package net.coljate.util;

import java.util.Iterator;
import java.util.function.Function;

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

}
