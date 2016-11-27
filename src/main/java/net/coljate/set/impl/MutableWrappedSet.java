package net.coljate.set.impl;

import net.coljate.set.MutableSet;

/**
 *
 * @author ollie
 */
public class MutableWrappedSet<T>
        extends WrappedSet<T>
        implements MutableSet<T> {

    private final java.util.Set<T> delegate;

    protected MutableWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public boolean add(final T element) {
        return delegate.add(element);
    }

    @Override
    public boolean addAll(Iterable<? extends T> elements) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public boolean removeAll(final Object element) {
        return delegate.remove(element);
    }

}
