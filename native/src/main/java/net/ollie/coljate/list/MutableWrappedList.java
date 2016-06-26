package net.ollie.coljate.list;

import net.ollie.coljate.list.mixin.WrapsMutableList;

/**
 *
 * @author Ollie
 */
public class MutableWrappedList<T>
        extends WrappedList<T>
        implements WrapsMutableList<T>, Cloneable {

    private final java.util.List<T> delegate;

    protected MutableWrappedList(final java.util.List<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.List<T> delegate() {
        return delegate;
    }

    @Override
    public java.util.List<T> copyDelegate() {
        return new java.util.ArrayList<>(delegate);
    }

    @Override
    public MutableList<T> tail() {
        if (this.isEmpty()) {
            return this;
        }
        final java.util.List<T> subList = delegate.subList(1, delegate.size());
        return new MutableWrappedList<>(subList);
    }

    @Override
    public MutableWrappedList<T> mutableCopy() {
        return new MutableWrappedList<>(this.copyDelegate());
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public MutableWrappedList<T> clone() {
        return this.mutableCopy();
    }

}
