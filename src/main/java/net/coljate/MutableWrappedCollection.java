package net.coljate;

import java.util.Collections;

/**
 *
 * @author ollie
 */
public class MutableWrappedCollection<T> extends WrappedCollection<T> implements MutableCollection<T> {

    public static <T> MutableCollection<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new MutableWrappedCollection<>(new java.util.ArrayList<>(collection));
    }

    private final java.util.Collection<T> delegate;

    protected MutableWrappedCollection(final java.util.Collection<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public boolean removeFirst(final T element) {
        return delegate.remove(element);
    }

    @Override
    public boolean removeAll(final T element) {
        return delegate.removeAll(Collections.singleton(element));
    }

}
