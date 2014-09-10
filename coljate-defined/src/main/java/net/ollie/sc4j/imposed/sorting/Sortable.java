package net.ollie.sc4j.imposed.sorting;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @see Sorter
 * @see Sorted
 */
public interface Sortable<T>
        extends Comparable<T> {

    @Nonnull
    Sorting sortWith(T that);

    @Override
    default int compareTo(final T that) {
        return this.sortWith(that).comparison();
    }

    public enum Sorting {

        BEFORE(-1),
        SAME(0),
        AFTER(1);

        private final int value;

        Sorting(final int comp) {
            this.value = comp;
        }

        public int comparison() {
            return value;
        }

        public static Sorting from(final int comparison) {
            return comparison == 0
                    ? SAME
                    : (comparison > 0 ? AFTER : BEFORE);
        }

    }

}
