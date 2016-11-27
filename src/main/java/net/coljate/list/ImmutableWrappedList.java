package net.coljate.list;

import net.coljate.ImmutableWrappedCollection;
import net.coljate.utils.Lists;

/**
 *
 * @author ollie
 */
public class ImmutableWrappedList<T>
        extends ImmutableWrappedCollection<T>
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
    protected java.util.List<T> mutableDelegateCopy() {
        return new java.util.ArrayList<>(delegate);
    }

    @Override
    @Deprecated
    public java.util.List<T> javaCollectionCopy() {
        return Lists.asUnmodifiableList(delegate);
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
        return MutableWrappedList.viewOf(this.mutableDelegateCopy());
    }

    @Override
    public ImmutableWrappedList<T> immutableCopy() {
        return this;
    }

}
