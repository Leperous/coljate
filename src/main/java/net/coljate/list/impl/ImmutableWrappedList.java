package net.coljate.list.impl;

import net.coljate.collection.Collection;
import net.coljate.list.ImmutableList;
import net.coljate.list.ImmutableListIterator;

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

    public static <T> ImmutableWrappedList<T> copyOf(final Collection<? extends T> collection) {
        final java.util.List<T> list = new java.util.ArrayList<>(collection.count());
        collection.forEach(list::add);
        return new ImmutableWrappedList<>(list);
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
    @Deprecated
    public ImmutableWrappedList<T> immutableCopy() {
        return this;
    }

}
