package net.coljate.collection;

import java.util.Comparator;

import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;
import net.coljate.list.Ordered;

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
    @TimeComplexity(value = Complexity.CONSTANT, worstCase = Complexity.LINEAR)
    T last();

    interface SortingAlgorithm {

        SortingAlgorithm DEFAULT = java.util.Arrays::sort;

        /**
         * Sort an array of comparable elements.
         */
        @TimeComplexity(bestCase = Complexity.LINEAR, worstCase = Complexity.QUADRATIC)
        default <T extends Comparable<? super T>> void sort(final T[] array) {
            this.sort(array, Comparator.naturalOrder());
        }

        /**
         * Sort an array of elements according to the given comparator.
         */
        @TimeComplexity(bestCase = Complexity.LINEAR, worstCase = Complexity.QUADRATIC)
        <T> void sort(T[] array, Comparator<? super T> comparator);

    }

}
