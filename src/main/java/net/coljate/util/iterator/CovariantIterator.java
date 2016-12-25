package net.coljate.util.iterator;

import java.util.Iterator;

/**
 *
 * @author ollie
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
