package net.coljate.list.impl;

import java.util.Comparator;

import net.coljate.collection.Collection;
import net.coljate.list.SortedArray;
import net.coljate.util.Arrays;

/**
 *
 * @author ollie
 */
public class ImmutableSortedArray<T>
        extends ImmutableNativeArray<T>
        implements SortedArray<T> {

    public static <T> SortedArray<T> sort(
            final Collection<T> collection,
            final Comparator<? super T> comparator) {
        return sort((T[]) collection.arrayCopy(), comparator);
    }

    public static <T> SortedArray<T> sort(
            final java.util.Collection<T> collection,
            final Comparator<? super T> comparator) {
        return sort((T[]) collection.toArray(), comparator);
    }

    public static <T> SortedArray<T> copyAndSort(
            final T[] array,
            final Comparator<? super T> comparator) {
        return sort(Arrays.copyOf(array), comparator);
    }

    private static <T> SortedArray<T> sort(final T[] array, final Comparator<? super T> comparator) {
        Arrays.sort(array, comparator);
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
