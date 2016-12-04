package net.coljate.list.impl;

import net.coljate.collection.impl.WrappedCollection;
import net.coljate.list.ImmutableList;
import net.coljate.list.List;
import net.coljate.list.ListIterator;
import net.coljate.list.MutableList;

/**
 *
 * @author ollie
 */
public class WrappedList<T> extends WrappedCollection<T> implements List<T> {

    public static <T> List<T> viewOf(final java.util.List<T> collection) {
        return new WrappedList<>(collection);
    }

    public static <T> List<T> copyOf(final java.util.Collection<? extends T> collection) {
        return viewOf(new java.util.ArrayList<>(collection));
    }

    private final java.util.List<T> delegate;

    protected WrappedList(final java.util.List<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.List<T> mutableJavaCopy() {
        return new java.util.ArrayList<>(delegate);
    }

    @Override
    public ListIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T last() {
        return delegate.get(delegate.size() - 1);
    }

    @Override
    public MutableList<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
