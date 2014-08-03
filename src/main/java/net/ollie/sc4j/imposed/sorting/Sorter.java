package net.ollie.sc4j.imposed.sorting;

import java.util.Comparator;

import net.ollie.sc4j.imposed.sorting.Sortable.Sorting;

import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 * An enumerated {@link Comparator}.
 *
 * @author Ollie
 * @see Sortable
 */
public interface Sorter<T>
        extends java.util.Comparator<T>, Serializable {

    static <T> Sorter<T> of(final java.util.Comparator<T> comparator) {
        return comparator instanceof Sorter
                ? (Sorter<T>) comparator
                : new SorterBridge<>(comparator);
    }

    @Nonnull
    Sorting sort(T t1, T t2);

    @Override
    default int compare(final T o1, final T o2) {
        return this.sort(o1, o2).comparison();
    }

    class SorterBridge<T> implements Sorter<T> {

        private static final long serialVersionUID = 1L;
        final java.util.Comparator<T> comparator;

        protected SorterBridge(final Comparator<T> comparator) {
            this.comparator = comparator;
        }

        @Override
        public Sorting sort(final T t1, final T t2) {
            return Sorting.from(comparator.compare(t1, t2));
        }

    }

}
