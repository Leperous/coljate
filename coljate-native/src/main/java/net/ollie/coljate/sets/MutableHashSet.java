package net.ollie.coljate.sets;

import java.util.Iterator;

/**
 *
 * @author Ollie
 */
public class MutableHashSet<T> extends NativeHashSet<T> implements MutableSet<T> {

    public static <T> MutableSet<T> copyOf(final java.util.Collection<? extends T> collection) {
        return viewOf(new java.util.HashSet<>(collection));
    }

    public static <T> MutableSet<T> viewOf(final java.util.HashSet<T> set) {
        return new MutableHashSet<>(set);
    }

    private final java.util.HashSet<T> delegate;

    MutableHashSet(final java.util.HashSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public ImmutableSet<T> immutableCopy() {
        return ImmutableHashSet.copyOf(delegate);
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
    public Set<T> tail() {
        throw new UnsupportedOperationException();
    }

}
