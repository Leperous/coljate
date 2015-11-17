package net.ollie.coljate.sets;

import java.util.Comparator;

/**
 *
 * @author Ollie
 */
public class MutableWrappedTreeSet<T> extends WrappedTreeSet<T> implements MutableSortedSet<T> {

    private final java.util.SortedSet<T> delegate;

    public MutableWrappedTreeSet(final java.util.SortedSet<T> delegate, final Comparator<? super T> comparator) {
        super(delegate, comparator);
        this.delegate = delegate;
    }

    @Override
    public boolean add(T element) {
        return delegate.add(element);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean remove(final Object element) {
        return delegate.remove(element);
    }

}
