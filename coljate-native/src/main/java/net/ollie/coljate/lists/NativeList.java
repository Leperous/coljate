package net.ollie.coljate.lists;

import java.util.Iterator;
import java.util.OptionalInt;

/**
 *
 * @author Ollie
 */
public abstract class NativeList<T> implements List<T> {

    public static <T> java.util.ArrayList<T> copyToList(final java.util.Collection<? extends T> collection) {
        return new java.util.ArrayList<>(collection);
    }

    private final java.util.List<? extends T> delegate;

    NativeList(final java.util.List<? extends T> delegate) {
        this.delegate = delegate;
    }

    protected java.util.ArrayList<T> copyDelegate() {
        return copyToList(delegate);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean contains(final Object object) {
        return delegate.contains(object);
    }

    @Override
    public T get(int index) {
        return delegate.get(index);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public OptionalInt indexOf(final Object element) {
        final int index = delegate.indexOf(element);
        return index >= 0 ? OptionalInt.of(index) : OptionalInt.empty();
    }

    @Override
    public MutableList<T> mutableCopy() {
        return MutableArrayList.copyOf(delegate);
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        return ImmutableArrayList.copyOf(delegate);
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

}
