package net.coljate.collection.sorting;

import net.coljate.util.Arrays;

import java.util.Comparator;

public class MergeSortAlgorithm implements SortingAlgorithm {

    public static final MergeSortAlgorithm INSTANCE = new MergeSortAlgorithm();

    private MergeSortAlgorithm() {
    }

    @Override
    public <T> void sort(final T[] array, final Comparator<? super T> comparator) {
        final T[] work = Arrays.copy(array);
        this.topDownSplitMerge(work, 0, work.length, array, comparator);
    }

    private <T> void topDownSplitMerge(final T[] left, final int start, final int end, final T[] right, final Comparator<? super T> comparator) {
        if (end - start < 2) {
            return;
        }
        final int middle = (start + end) / 2;
        this.topDownSplitMerge(right, start, middle, left, comparator);
        this.topDownSplitMerge(right, middle, end, left, comparator);
        this.topDownMerge(left, start, middle, end, right, comparator);
    }

    private <T> void topDownMerge(final T[] left, final int start, final int middle, final int end, final T[] right, final Comparator<? super T> comparator) {
        int i = start, j = middle;
        for (int k = start; k < end; k++) {
            if (i < middle && (j >= end || comparator.compare(left[i], right[j]) <= 0)) {
                right[k] = left[i];
                i++;
            } else {
                right[k] = left[j];
                j++;
            }
        }
    }

}
