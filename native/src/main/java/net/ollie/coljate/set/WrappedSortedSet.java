package net.ollie.coljate.set;

import java.util.Comparator;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.set.mixin.CopiedToTreeSet;

/**
 *
 * @author Ollie
 */
public class WrappedSortedSet<T>
        extends WrappedSet<T>
        implements SortedSet<T>, CopiedToTreeSet<T> {

    private final java.util.SortedSet<T> delegate;

    public WrappedSortedSet(final java.util.SortedSet<T> delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    protected WrappedSortedSet<T> create(final java.util.SortedSet<T> delegate) {
        return new WrappedSortedSet<>(delegate);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Comparator<? super T> comparator() {
        final Comparator<? super T> comparator = delegate.comparator();
        return comparator == null ? (Comparator) Comparator.naturalOrder() : comparator;
    }

    @Override
    public SortedSet<T> subSet(final T min, final T max) {
        return this.create(delegate.subSet(min, max));
    }

    @Override
    public SortedSet<T> tail() {
        return this.create(delegate.tailSet(this.head()));
    }

    @Override
    public T max() {
        return delegate.last();
    }

    @Override
    public T min() {
        return delegate.first();
    }

    @Override
    public MutableSortedSet<T> mutableCopy() {
        return CopiedToTreeSet.super.mutableCopy();
    }

    @Override
    public ImmutableSortedSet<T> immutableCopy() {
        return CopiedToTreeSet.super.immutableCopy();
    }

}
