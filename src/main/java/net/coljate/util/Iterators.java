package net.coljate.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
        return filter(iterator::hasNext, iterator::next, predicate);
    }

    public static <T> Iterator<T> filter(
            final BooleanSupplier canGenerateCandidate,
            final Supplier<? extends T> generateCandidate,
            final Predicate<? super T> isValid) {
        return new FilteredIterator<T>() {

            @Override
            protected boolean canGenerateCandidate() {
                return canGenerateCandidate.getAsBoolean();
            }

            @Override
            protected T generateCandidate() {
                return generateCandidate.get();
            }

            @Override
            protected boolean isValid(T candidate) {
                return isValid.test(candidate);
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

    public static <T> T last(final Iterator<? extends T> iterator) {
        T last = null;
        while (iterator.hasNext()) {
            last = iterator.next();
        }
        return last;
    }

    public static abstract class FilteredIterator<T> implements Iterator<T> {

        boolean hasNext;
        T next;

        protected abstract boolean canGenerateCandidate();

        protected abstract T generateCandidate();

        protected abstract boolean isValid(T candidate);

        @Override
        public boolean hasNext() {
            if (hasNext) {
                return true;
            }
            while (this.canGenerateCandidate()) {
                final T candidate = this.generateCandidate();
                if (this.isValid(candidate)) {
                    next = candidate;
                    hasNext = true;
                    break;
                }
            }
            return hasNext;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            hasNext = false;
            return next;
        }

    }

}
