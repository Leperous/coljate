package net.coljate.collection.sorting;

import java.util.Comparator;

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

    private <T> int partition(final T[] array, final Comparator<? super T> comparator, final int low, final int high) {
        int partition = low;
        for (int i = low; i < high; i++) {
            if (comparator.compare(array[i], array[high]) < 0) {
                SortingAlgorithm.swap(array, partition++, i);
            }
        }
        SortingAlgorithm.swap(array, partition, high);
        return partition;
    }

}
