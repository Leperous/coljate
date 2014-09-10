package net.ollie.coljate.imposed.sorting;

import net.ollie.coljate.imposed.sorting.Sortable.Sorting;

import javax.annotation.Nonnull;
import java.io.Serializable;

import java.util.Comparator;

/**
 * An enumerated {@link Comparator}.
 *
 * @author Ollie
 * @see Sortable
 */
public interface Sorter<T>
        extends java.util.Comparator<T>, Serializable {

    @Nonnull
    Sorting sort(T t1, T t2);

    @Override
    default int compare(final T o1, final T o2) {
        return this.sort(o1, o2).comparison();
    }

    static <T> Sorter<T> create(final java.util.Comparator<T> comparator) {
        return comparator instanceof Sorter
                ? (Sorter<T>) comparator
                : new SorterBridge<>(comparator);
    }

    @SuppressWarnings("unchecked")
    static <T extends Comparable<? super T>> Sorter<T> natural() {
        return SorterBridge.NATURAL;
    }

    class SorterBridge<T> implements Sorter<T> {

        @SuppressWarnings({"rawtypes", "unchecked"})
        private static final SorterBridge NATURAL = new SorterBridge(Comparator.naturalOrder());
        private static final long serialVersionUID = 1L;
        private final java.util.Comparator<T> comparator;

        protected SorterBridge(final Comparator<T> comparator) {
            this.comparator = comparator;
        }

        @Override
        public Sorting sort(final T t1, final T t2) {
            return Sorting.from(comparator.compare(t1, t2));
        }

    }

}
