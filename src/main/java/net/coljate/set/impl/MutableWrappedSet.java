package net.coljate.set.impl;

import java.util.Iterator;

import net.coljate.set.MutableSet;
import net.coljate.set.Set;
import net.coljate.util.Arrays;

/**
 *
 * @author ollie
 */
public class MutableWrappedSet<T>
        extends WrappedSet<T>
        implements MutableSet<T> {

    public static <T> MutableWrappedSet<T> of() {
        return viewOf(new java.util.HashSet<>());
    }

    @SafeVarargs
    public static <T> MutableWrappedSet<T> copyIntoHashSet(final T... elements) {
        final java.util.Set<T> set = new java.util.HashSet<>(elements.length);
        Arrays.consume(elements, set::add);
        return viewOf(set);
    }

    public static <T> MutableWrappedSet<T> copyIntoHashSet(final java.util.Collection<? extends T> collection) {
        return viewOf(new java.util.HashSet<>(collection));
    }

    public static <T> MutableWrappedSet<T> copyOf(final Set<T> set) {
        return viewOf(set.mutableJavaCopy());
    }

    public static <T> MutableWrappedSet<T> viewOf(final java.util.Set<T> set) {
        return new MutableWrappedSet<>(set);
    }

    private final java.util.Set<T> delegate;

    protected MutableWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public boolean add(final T element) {
        return delegate.add(element);
    }

    @Override
    public boolean addAll(Iterable<? extends T> elements) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public boolean remove(final Object element) {
        return delegate.remove(element);
    }

}
