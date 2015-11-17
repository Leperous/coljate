package net.ollie.coljate.sets;

import java.util.Comparator;
import static java.util.Objects.requireNonNull;
import java.util.TreeSet;
import java.util.function.Supplier;

/**
 *
 * @author Ollie
 */
public class WrappedTreeSet<T> extends WrappedSortedSet<T> {

    public static <T> java.util.TreeSet<T> viewAsTreeSet(final java.util.Collection<T> collection, final Supplier<Comparator<? super T>> comparator) {
        return collection instanceof java.util.TreeSet
                ? (java.util.TreeSet<T>) collection
                : copyIntoTreeSet(collection, comparator.get());
    }

    public static <T> java.util.TreeSet<T> copyIntoTreeSet(final java.util.Collection<? extends T> collection, final Comparator<? super T> comparator) {
        requireNonNull(comparator);
        final TreeSet<T> set = new java.util.TreeSet<>(comparator);
        set.addAll(collection);
        return set;
    }

    final java.util.TreeSet<T> delegate;

    protected WrappedTreeSet(final java.util.TreeSet<T> delegate, final Comparator<? super T> comparator) {
        super(delegate, comparator);
        this.delegate = delegate;
    }

}
