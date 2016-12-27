package net.coljate.util.iterator;

import java.util.Iterator;

/**
 * An iterator that allows generic declaration of a base type {@code <T>} but can be covariantly subclassed as some
 * {@code <R extends T>} on the elements returned by {@link #next next()}.
 *
 * @author ollie
 * @param <T> inherited type.
 * @param <R> {@link #next} type.
 */
public interface CovariantIterator<T, R extends T> extends Iterator<T> {

    @Override
    R next();

    static <T, R extends T> CovariantIterator<T, R> of(final Iterator<R> iterator) {
        return new CovariantIterator<T, R>() {

            @Override
            public R next() {
                return iterator.next();
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public void remove() {
                iterator.remove();
            }

        };
    }

}
