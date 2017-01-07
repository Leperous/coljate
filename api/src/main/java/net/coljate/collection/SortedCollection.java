package net.coljate.collection;

import java.util.Comparator;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.coljate.list.Ordered;
import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

/**
 * Has a specific order, based on some {@link #comparator}.
 *
 * @author ollie
 */
public interface SortedCollection<T> extends Ordered<T>, Collection<T> {

    /**
     * @return the comparator used to sort this collection.
     */
    @Nonnull
    Comparator<? super T> comparator();

    @CheckForNull
    T least();

    /**
     * @return the greatest or last element in this collection, according to the sort.
     */
    @CheckForNull
    T greatest();

    @Override
    default T first() {
        return this.least();
    }

    interface SortingAlgorithm {

        @SuppressWarnings("methodref.param.invalid")
        SortingAlgorithm DEFAULT = java.util.Arrays::sort;

        /**
         * Sort an array of naturally comparable elements.
         */
        @TimeComplexity(bestCase = Complexity.LINEAR, worstCase = Complexity.QUADRATIC)
        @SuppressWarnings("type.argument.type.incompatible")
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
