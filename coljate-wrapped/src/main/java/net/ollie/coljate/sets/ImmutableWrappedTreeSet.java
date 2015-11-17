package net.ollie.coljate.sets;

import java.util.Comparator;

import net.ollie.coljate.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedTreeSet<T> extends WrappedTreeSet<T> implements ImmutableSortedSet<T> {

    public static <T extends Comparable<? super T>> ImmutableSortedSet<T> copyOf(final java.util.Collection<? extends T> collection) {
        return copyOf(collection, Comparator.naturalOrder());
    }

    public static <T> ImmutableSortedSet<T> copyOf(final java.util.Collection<? extends T> collection, final Comparator<? super T> comparator) {
        return new ImmutableWrappedTreeSet<>(copyIntoTreeSet(collection, comparator));
    }

    ImmutableWrappedTreeSet(final java.util.TreeSet<T> delegate) {
        super(delegate, delegate.comparator());
    }

    @Override
    public ImmutableWrappedTreeSet<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableSortedSet<T> subSet(final T min, final T max) {
        return new ImmutableWrappedTreeSet<>(viewAsTreeSet(delegate.subSet(min, max), delegate::comparator));
    }

    @Override
    public ImmutableWrappedTreeSet<T> with(final T element) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

}
