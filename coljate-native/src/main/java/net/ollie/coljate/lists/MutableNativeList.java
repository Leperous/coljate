package net.ollie.coljate.lists;

import java.util.Iterator;
import java.util.RandomAccess;

/**
 *
 * @author Ollie
 * @see List
 */
public class MutableNativeList<T> extends NativeList<T> implements MutableList<T>, RandomAccess {

    private final java.util.List<T> delegate;

    protected MutableNativeList(final java.util.List<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    @Override
    public boolean add(final T element) {
        return delegate.add(element);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean remove(final Object element) {
        return delegate.remove(element);
    }

    @Override
    public T set(final int index, final T element) {
        return delegate.set(index, element);
    }

    @Override
    public MutableList<T> tail() {
        final java.util.List<T> tail = delegate.subList(1, delegate.size() - 1);
        return new MutableNativeList<>(tail);
    }

}
