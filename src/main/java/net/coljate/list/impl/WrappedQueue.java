package net.coljate.list.impl;

import java.util.Optional;

import net.coljate.collection.MutableWrappedCollection;
import net.coljate.list.ImmutableList;
import net.coljate.list.Queue;

/**
 *
 * @author ollie
 */
public class WrappedQueue<T>
        extends MutableWrappedCollection<T>
        implements Queue<T> {

    private final java.util.Queue<T> delegate;

    protected WrappedQueue(final java.util.Queue<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public T head() {
        return delegate.peek();
    }

    @Override
    public Optional<T> removeHead() {
        return Optional.ofNullable(delegate.poll());
    }

    @Override
    public T poll() {
        return delegate.poll();
    }

    @Override
    public T remove() {
        return delegate.remove();
    }

    @Override
    public WrappedQueue<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
