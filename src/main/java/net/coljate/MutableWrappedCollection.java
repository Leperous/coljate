package net.coljate;

import java.util.Collections;

import net.coljate.util.NativeCollections;

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

    public boolean add(final T element) {
        return delegate.add(element);
    }

    @Override
    public boolean removeFirst(final Object element) {
        return delegate.remove(element);
    }

    @Override
    public boolean removeAll(final Object element) {
        return delegate.removeAll(Collections.singleton(element));
    }

    @Override
    public boolean removeAll(final Iterable<?> elements) {
        return delegate.removeAll(NativeCollections.asCollection(elements));
    }

    @Override
    public void clear() {
        delegate.clear();
    }

}
