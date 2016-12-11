package net.coljate.set;

import java.util.Comparator;

import net.coljate.collection.Collection;
import net.coljate.collection.SortedCollection;
import net.coljate.set.impl.MutableWrappedTreeSet;

/**
 *
 * @author ollie
 * @see java.util.SortedSet
 */
public interface SortedSet<T> extends SortedCollection<T>, Set<T> {

    static <T> SortedSet<T> copyOf(
            final Comparator<? super T> comparator,
            final Collection<? extends T> collection) {
        return MutableWrappedTreeSet.copyOf(comparator, collection);
    }

}
