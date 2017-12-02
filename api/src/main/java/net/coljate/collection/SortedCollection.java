package net.coljate.collection;

import net.coljate.sequence.FiniteSequence;
import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Has a specific order, based on some {@link #comparator}.
 *
 * @author Ollie
 */
public interface SortedCollection<T> extends FiniteSequence<T> {

    /**
     * @return the comparator used to sort this collection. (Not the {@link SortingAlgorithm} algorithm!)
     */
    @Nonnull
    Comparator<? super T> comparator();

    @CheckForNull
    default T least() {
        return this.first();
    }

    /**
     * @return the greatest or last element in this collection, according to the sort.
     */
    @CheckForNull
    T greatest();

    @Override
    default T first() {
        final Iterator<T> iterator = this.iterator();
        return iterator.hasNext() ? null : iterator.next();
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
