package net.ollie.coljate.set;

import java.io.Serializable;
import java.util.Comparator;

import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.set.mixin.WrapsTreeSet;
import net.ollie.coljate.theory.Finite;
import net.ollie.coljate.theory.Sorted;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedTreeSet<T>
        extends WrappedTreeSet<T>
        implements ImmutableSortedSet<T>, Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    public static <S extends Finite<T> & Sorted<T>, T> ImmutableSortedSet<T> copyOf(final S collection) {
        return copyOf(collection, collection.comparator());
    }

    public static <T> ImmutableSortedSet<T> copyOf(final Finite<T> collection, final Comparator<? super T> comparator) {
        return new ImmutableWrappedTreeSet<>(WrapsTreeSet.copyIntoTreeSet(collection, comparator));
    }

    public static <T extends Comparable<? super T>> ImmutableSortedSet<T> copyOf(final java.util.Collection<? extends T> collection) {
        return copyOf(collection, Comparator.naturalOrder());
    }

    public static <T> ImmutableSortedSet<T> copyOf(final java.util.Collection<? extends T> collection, final Comparator<? super T> comparator) {
        return new ImmutableWrappedTreeSet<>(WrapsTreeSet.copyIntoTreeSet(collection, comparator));
    }

    private final java.util.TreeSet<T> delegate;

    ImmutableWrappedTreeSet(final java.util.TreeSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public ImmutableWrappedTreeSet<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableSortedSet<T> subSet(final T min, final T max) {
        return new ImmutableWrappedTreeSet<>(WrapsTreeSet.viewAsTreeSet(delegate.subSet(min, max), delegate::comparator));
    }

    @Override
    public ImmutableWrappedTreeSet<T> with(final T element) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    @Deprecated
    public ImmutableWrappedTreeSet<T> immutableCopy() {
        return this;
    }

    @Override
    public ImmutableWrappedTreeSet<T> clone() {
        return new ImmutableWrappedTreeSet<>(this.copyDelegate());
    }

}
