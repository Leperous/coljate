package net.coljate.list.impl;

import net.coljate.list.ImmutableList;
import net.coljate.list.MutableList;

/**
 *
 * @author ollie
 */
public class ImmutableWrappedList<T>
        extends WrappedList<T>
        implements ImmutableList<T> {

    public static <T> ImmutableWrappedList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new ImmutableWrappedList<>(new java.util.ArrayList<>(collection));
    }

    private final java.util.List<T> delegate;

    protected ImmutableWrappedList(final java.util.List<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public ImmutableListIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableList<T> prefixed(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableList<T> suffixed(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MutableList<T> mutableCopy() {
        return MutableWrappedList.viewOf(this.mutableJavaCopy());
    }

    @Override
    public ImmutableWrappedList<T> immutableCopy() {
        return this;
    }

}
