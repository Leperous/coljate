package net.coljate.list.impl;

import java.util.Comparator;

import net.coljate.collection.Collection;
import net.coljate.list.SortedArray;
import net.coljate.util.Arrays;
import net.coljate.feature.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public class ImmutableSortedArray<T>
        extends ImmutableNativeArray<T>
        implements SortedArray<T> {

    public static <T extends Comparable<? super T>> SortedArray<T> sort(final Collection<T> collection) {
        return sort(collection, Comparator.naturalOrder(), SortingAlgorithm.DEFAULT);
    }

    @TimeComplexity(computed = true)
    public static <T> SortedArray<T> sort(
            final Collection<T> collection,
            final Comparator<? super T> comparator,
            final SortingAlgorithm sortingAlgorithm) {
        return sort((T[]) collection.arrayCopy(), comparator, sortingAlgorithm);
    }

    @TimeComplexity(computed = true)
    public static <T extends Comparable<? super T>> SortedArray<T> sort(final java.util.Collection<T> collection) {
        return sort(collection, Comparator.naturalOrder(), SortingAlgorithm.DEFAULT);
    }

    @TimeComplexity(computed = true)
    public static <T> SortedArray<T> sort(
            final java.util.Collection<T> collection,
            final Comparator<? super T> comparator,
            final SortingAlgorithm sortingAlgorithm) {
        return sort((T[]) collection.toArray(), comparator, sortingAlgorithm);
    }

    @TimeComplexity(computed = true)
    public static <T> SortedArray<T> copyAndSort(
            final T[] array,
            final Comparator<? super T> comparator,
            final SortingAlgorithm sortingAlgorithm) {
        return sort(Arrays.copyOf(array), comparator, sortingAlgorithm);
    }

    @TimeComplexity(computed = true)
    private static <T> SortedArray<T> sort(final T[] array, final Comparator<? super T> comparator, final SortingAlgorithm sortingAlgorithm) {
        sortingAlgorithm.sort(array, comparator);
        return new ImmutableSortedArray<>(array, comparator);
    }

    private final Comparator<? super T> comparator;

    protected ImmutableSortedArray(final Object[] array, final Comparator<? super T> comparator) {
        super(array);
        this.comparator = comparator;
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

}
