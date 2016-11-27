package net.coljate.set.impl;

import java.util.Comparator;

import net.coljate.set.SortedSet;
import net.coljate.util.Suppliers;

/**
 *
 * @author ollie
 */
public class MutableWrappedTreeSet<T>
        extends MutableWrappedSet<T>
        implements SortedSet<T> {

    private final java.util.TreeSet<T> delegate;

    public MutableWrappedTreeSet(final java.util.TreeSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    protected java.util.TreeSet<T> mutableDelegateCopy() {
        return new java.util.TreeSet<>(delegate);
    }

    @Override
    public Comparator<? super T> comparator() {
        return Suppliers.firstNonNull(delegate.comparator(), (Comparator) Comparator.naturalOrder());
    }

    @Override
    public T first() {
        return delegate.first();
    }

    @Override
    public T last() {
        return delegate.last();
    }

}
