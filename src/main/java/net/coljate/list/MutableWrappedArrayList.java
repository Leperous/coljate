package net.coljate.list;

import java.io.Serializable;

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
    public T get(int index) {
        return delegate.get(index);
    }

    @Override
    public int length() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableNativeArray<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
