package net.coljate.list.impl;

import java.util.ArrayDeque;
import java.util.Optional;

import net.coljate.collection.impl.MutableWrappedCollection;
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
    public Optional<T> peek() {
        return Optional.ofNullable(delegate.peek());
    }

    @Override
    public Optional<T> poll() {
        return Optional.ofNullable(delegate.poll());
    }

    @Override
    public WrappedQueue<T> mutableCopy() {
        return new WrappedQueue<>(new ArrayDeque<>(delegate));
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        return ImmutableNativeArray.copyOf(this);
    }

}
