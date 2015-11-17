package net.ollie.coljate.sets;

import java.util.Comparator;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Ollie
 */
public class WrappedSortedSet<T> extends WrappedSet<T> implements SortedSet<T> {

    private final java.util.SortedSet<T> delegate;
    private final Comparator<? super T> comparator;

    protected WrappedSortedSet(final java.util.SortedSet<T> delegate, final Comparator<? super T> comparator) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
        this.comparator = requireNonNull(comparator);
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public SortedSet<T> subSet(final T min, final T max) {
        return new WrappedSortedSet<>(delegate.subSet(min, max), comparator);
    }

    @Override
    public MutableSortedSet<T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableSortedSet<T> immutableCopy() {
        return ImmutableWrappedTreeSet.copyOf(delegate, comparator);
    }

    @Override
    public T max() {
        return delegate.last();
    }

    @Override
    public T min() {
        return delegate.first();
    }

}
