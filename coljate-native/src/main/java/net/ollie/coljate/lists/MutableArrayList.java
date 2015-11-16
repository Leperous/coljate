package net.ollie.coljate.lists;

import java.util.Iterator;
import java.util.RandomAccess;

/**
 *
 * @author Ollie
 */
public class MutableArrayList<T> extends NativeArrayList<T> implements MutableList<T>, RandomAccess {

    public static <T> MutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new MutableArrayList<>(new java.util.ArrayList<>(collection));
    }

    public static <T> MutableList<T> viewOf(final java.util.ArrayList<T> list) {
        return new MutableArrayList<>(list);
    }

    private final java.util.List<T> delegate;

    protected MutableArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
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

    @Override
    public T set(final int index, final T element) {
        return delegate.set(index, element);
    }

    @Override
    public MutableList<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
