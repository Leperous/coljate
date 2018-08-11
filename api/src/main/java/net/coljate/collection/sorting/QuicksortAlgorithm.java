package net.coljate.collection.sorting;

import java.util.Comparator;

/**
 * Select a partition index, and divide and sort the rest of the array into two parts, one where the values are are
 * less than the value at the partition index (the pivot) and the other where they are greater than the pivot.
 * <p>
 * For example suppose you have an array {@code [3, 2, 5, 1, 4]}.
 */
class QuicksortAlgorithm implements SortingAlgorithm {

    @Override
    public <T> void sort(final T[] array, final Comparator<? super T> comparator) {
        this.quicksort(array, comparator, 0, array.length - 1);
    }

    private <T> void quicksort(final T[] array, final Comparator<? super T> comparator, final int low, final int high) {
        if (high > low) {
            final int partition = this.partition(array, comparator, low, high);
            this.quicksort(array, comparator, low, partition - 1);
            this.quicksort(array, comparator, partition + 1, high);
        }
    }

    private <T> int partition(final T[] array, final Comparator<? super T> comparator, final int start, final int end) {
        //Lomuto partition scheme
        int partition = start;
        for (int i = start; i < end; i++) {
            if (comparator.compare(array[i], array[end]) < 0) {
                SortingAlgorithm.swap(array, partition++, i);
            }
        }
        SortingAlgorithm.swap(array, partition, end);
        return partition;
    }

}
