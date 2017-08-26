package net.coljate.set;

import java.util.Comparator;

import net.coljate.collection.Collection;
import net.coljate.collection.SortedCollection;
import net.coljate.set.impl.MutableWrappedTreeSet;
import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

/**
 *
 * @author ollie
 * @see java.util.SortedSet
 */
public interface SortedSet<T> extends SortedCollection<T>, SequentialSet<T> {

    @Override
    default T first() {
        return SortedCollection.super.first();
    }

    @TimeComplexity(computed = true, bestCase = Complexity.LINEAR)
    static <T> SortedSet<T> copyOf(
            final Comparator<? super T> comparator,
            final Collection<? extends T> collection) {
        return MutableWrappedTreeSet.copyOf(comparator, collection);
    }

}
