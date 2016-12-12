package net.coljate.list.impl;

import java.util.ArrayDeque;

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

    @SafeVarargs
    public static <T> WrappedQueue<T> copyOf(final T... elements) {
        final java.util.Queue<T> queue = new ArrayDeque<>(elements.length);
        for (int i = 0; i < elements.length; i++) {
            queue.add(elements[i]);
        }
        return new WrappedQueue<>(queue);
    }

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
    public T remove() {
        return delegate.remove();
    }

    @Override
    public T poll() {
        return delegate.poll();
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
