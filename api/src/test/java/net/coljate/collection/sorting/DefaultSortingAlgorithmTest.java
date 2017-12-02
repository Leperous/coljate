package net.coljate.collection.sorting;

import javax.annotation.Nonnull;

public class DefaultSortingAlgorithmTest implements SortingAlgorithmTest {

    @Nonnull
    @Override
    public SortingAlgorithm algorithm() {
        return SortingAlgorithm.JAVA_DEFAULT;
    }

}
