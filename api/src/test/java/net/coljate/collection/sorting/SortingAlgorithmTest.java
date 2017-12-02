package net.coljate.collection.sorting;

import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public interface SortingAlgorithmTest {

    @Nonnull
    SortingAlgorithm algorithm();

    @Test
    default void shouldSortEmpty() {
        final int[] ints = new int[0];
        this.algorithm().sort(ints);
        assertThat(ints, is(new int[0]));
    }

    @Test
    default void shouldSortSingleton() {
        final int[] ints = {1};
        this.algorithm().sort(ints);
        assertThat(ints, is(new int[]{1}));
    }

    @Test
    default void shouldSortTwoElements() {
        final int[] ints = {3, 2};
        this.algorithm().sort(ints);
        assertThat(ints, is(new int[]{2, 3}));
    }

    @Test
    default void shouldSortThreeElements() {
        final int[] ints = {7, 3, 5};
        this.algorithm().sort(ints);
        assertThat(ints, is(new int[]{3, 5, 7}));
    }

}