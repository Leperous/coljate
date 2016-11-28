package net.coljate.list.impl;

import java.io.Serializable;

import net.coljate.list.MutableArray;

/**
 *
 * @author ollie
 */
public class MutableWrappedArrayList<T>
        extends MutableWrappedList<T>
        implements MutableArray<T>, Serializable {

    private static final long serialVersionUID = 1L;

    private final java.util.ArrayList<T> delegate;

    protected MutableWrappedArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public T get(final int index) {
        return delegate.get(index);
    }

    @Override
    public T set(final int i, final T value) {
        return delegate.set(i, value);
    }

    @Override
    public int length() {
        return delegate.size();
    }

    @Override
    public ImmutableNativeArray<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
