package net.coljate.list.impl;

import net.coljate.list.ListIterator;
import net.coljate.list.MutableList;

/**
 *
 * @author ollie
 */
public class SynchronizedList<T> implements MutableList<T> {

    private final MutableList<T> delegate;

    public SynchronizedList(final MutableList<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public synchronized void prefix(T element) {
        delegate.prefix(element);
    }

    @Override
    public synchronized void suffix(T element) {
        delegate.suffix(element);
    }

    @Override
    public ListIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public synchronized T last() {
        return delegate.last();
    }

    @Override
    public synchronized void clear() {
        delegate.clear();
    }

    @Override
    public synchronized boolean removeFirst(final Object element) {
        return delegate.removeFirst(element);
    }

    @Override
    public boolean removeAll(final Object element) {
        return delegate.removeAll(element);
    }

}
