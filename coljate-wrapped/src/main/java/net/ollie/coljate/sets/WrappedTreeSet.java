package net.ollie.coljate.sets;

import java.util.Comparator;

/**
 *
 * @author Ollie
 */
public abstract class WrappedTreeSet<T> extends WrappedSet<T> implements SortedSet<T> {

    private final java.util.SortedSet<T> delegate;
    private final Comparator<? super T> comparator;

    protected WrappedTreeSet(final java.util.SortedSet<T> delegate, final Comparator<? super T> comparator) {
        super(delegate);
        this.delegate = delegate;
        this.comparator = comparator;
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public MutableSortedSet<T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableSortedSet<T> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

}
