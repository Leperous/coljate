package net.coljate.util.iterator;

import java.util.Iterator;
import java.util.function.Function;

/**
 *
 * @author ollie
 */
public interface UnmodifiableCovariantIterator<T, R extends T>
        extends UnmodifiableIterator<T>, CovariantIterator<T, R> {

    static <T, R extends T> UnmodifiableCovariantIterator<T, R> wrap(final Iterator<R> iterator) {
        return new UnmodifiableCovariantIterator<T, R>() {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public R next() {
                return iterator.next();
            }

        };
    }

    static <T, R extends T> UnmodifiableCovariantIterator<T, R> wrap(final Iterator<T> iterator, final Function<? super T, ? extends R> transform) {
        return new UnmodifiableCovariantIterator<T, R>() {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public R next() {
                return transform.apply(iterator.next());
            }

        };
    }

    static <T, R extends T> UnmodifiableCovariantIterator<T, R> wrap(final CovariantIterator<T, R> iterator) {
        return new UnmodifiableCovariantIterator<T, R>() {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public R next() {
                return iterator.next();
            }

        };
    }

}