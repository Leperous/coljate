package net.ollie.coljate.set;

import java.util.Comparator;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.set.mixin.WrapsMutableSet;
import net.ollie.coljate.theory.Finite;
import net.ollie.coljate.theory.Sorted;

/**
 *
 * @author Ollie
 */
public class MutableWrappedTreeSet<T>
        extends WrappedSortedSet<T>
        implements MutableSortedSet<T>, WrapsMutableSet<T> {

    public static <S extends Finite<T> & Sorted<T>, T> MutableSortedSet<T> copyOf(final S collection) {
        return copyOf(collection, collection.comparator());
    }

    public static <T> MutableSortedSet<T> copyOf(final Finite<T> collection, final Comparator<? super T> comparator) {
        final java.util.TreeSet<T> treeSet = new java.util.TreeSet<>(comparator);
        collection.forEach(treeSet::add);
        return new MutableWrappedTreeSet<>(treeSet);
    }

    public static <T> MutableSortedSet<T> viewOf(final java.util.TreeSet<T> set) {
        return new MutableWrappedTreeSet<>(set);
    }

    private final java.util.TreeSet<T> delegate;

    MutableWrappedTreeSet(final java.util.TreeSet<T> delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public java.util.TreeSet<T> delegate() {
        return delegate;
    }

}
