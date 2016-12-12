package net.coljate.collection;

import java.util.Comparator;

import net.coljate.feature.Complexity;
import net.coljate.feature.Ordered;

/**
 * Has a specific order, based on some {@link #comparator}.
 *
 * @author ollie
 */
public interface SortedCollection<T> extends Ordered<T>, Collection<T> {

    /**
     * @return the comparator used to sort this collection.
     */
    Comparator<? super T> comparator();

    /**
     * @return the last element in this collection, according to the sort.
     */
    @Complexity(value = Complexity.Order.CONSTANT, worstCase = Complexity.Order.LINEAR)
    T last();

    interface SortingAlgorithm {

        SortingAlgorithm DEFAULT = java.util.Arrays::sort;

        /**
         * Sort an array of comparable elements.
         */
        @Complexity(bestCase = Complexity.Order.LINEAR, worstCase = Complexity.Order.QUADRATIC)
        default <T extends Comparable<? super T>> void sort(final T[] array) {
            this.sort(array, Comparator.naturalOrder());
        }

        /**
         * Sort an array of elements according to the given comparator.
         */
        @Complexity(bestCase = Complexity.Order.LINEAR, worstCase = Complexity.Order.QUADRATIC)
        <T> void sort(T[] array, Comparator<? super T> comparator);

    }

}
