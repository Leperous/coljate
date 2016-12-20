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
public class WrappedList<T>
        extends WrappedCollection<T>
        implements List<T> {

    public static <T> List<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new WrappedList<>(new java.util.ArrayList<>(collection));
    }

    private final java.util.List<? extends T> delegate;

    public WrappedList(final java.util.List<? extends T> delegate) {
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
        return new MutableWrappedList<>(this.mutableJavaCopy());
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        return new ImmutableWrappedList<>(this.mutableJavaCopy());
    }

}
