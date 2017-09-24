package net.coljate.set;

import java.util.Comparator;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.coljate.collection.Collection;
import net.coljate.collection.SortedCollection;
import net.coljate.set.impl.MutableWrappedTreeSet;
import net.coljate.set.lazy.LazyFilteredSortedSet;
import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

/**
 *
 * @author Ollie
 * @see java.util.SortedSet
 */
public interface SortedSet<T> extends SortedCollection<T>, SequentialSet<T> {

    @Override
    default T first() {
        return SortedCollection.super.first();
    }

    @Nonnull
    @CheckReturnValue
    default SortedSet<T> greaterThan(final T element) {
        return LazyFilteredSortedSet.of(this, e -> this.comparator().compare(e, element) > 0);
    }

    @Nonnull
    @CheckReturnValue
    default SortedSet<T> lessThan(final T element) {
        return LazyFilteredSortedSet.of(this, e -> this.comparator().compare(e, element) < 0);
    }

    @TimeComplexity(computed = true, bestCase = Complexity.LINEAR)
    static <T> SortedSet<T> copyOf(
            final Comparator<? super T> comparator,
            final Collection<? extends T> collection) {
        return MutableWrappedTreeSet.copyOf(comparator, collection);
    }

}
