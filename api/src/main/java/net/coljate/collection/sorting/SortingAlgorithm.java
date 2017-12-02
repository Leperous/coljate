package net.coljate.collection.sorting;

import net.coljate.util.Arrays;
import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

import java.util.Comparator;

public interface SortingAlgorithm {

    SortingAlgorithm DEFAULT = java.util.Arrays::sort;

    /**
     * Sort an array of elements according to the given comparator.
     */
    @TimeComplexity(bestCase = Complexity.LINEAR, worstCase = Complexity.QUADRATIC)
    <T> void sort(T[] array, Comparator<? super T> comparator);

    /**
     * Sort an array of naturally comparable elements.
     */
    @TimeComplexity(bestCase = Complexity.LINEAR, worstCase = Complexity.QUADRATIC)
    @SuppressWarnings("type.argument.type.incompatible")
    default <T extends Comparable<? super T>> void sort(final T[] array) {
        this.sort(array, Comparator.naturalOrder());
    }

    default void sort(final int[] array) {
        final Integer[] integers = Arrays.copyIntArray(array);
        this.sort(integers);
        Arrays.writeIntArray(integers, array);
    }

}
