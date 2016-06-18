package net.ollie.coljate.list;

import java.util.Objects;
import java.util.OptionalInt;

import net.ollie.coljate.WrappedCollection;
import net.ollie.coljate.list.mixin.CopiedToList;
import net.ollie.coljate.list.mixin.WrapsList;

/**
 *
 * @author Ollie
 */
public class WrappedList<T>
        extends WrappedCollection<T>
        implements WrapsList<T>, CopiedToList<T> {

    private final java.util.List<T> delegate;

    public WrappedList(final java.util.List<T> delegate) {
        super(delegate);
        this.delegate = Objects.requireNonNull(delegate);
    }

    @Override
    public java.util.List<T> copyDelegate() {
        return new java.util.ArrayList<>(delegate);
    }

    @Override
    public T get(final int index) {
        return delegate.get(index);
    }

    @Override
    public OptionalInt indexOf(final Object element) {
        final int index = delegate.indexOf(element);
        return index >= 0 ? OptionalInt.of(index) : OptionalInt.empty();
    }

    @Override
    public List<T> tail() {
        return new WrappedList<>(this.nativeTail());
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        return WrapsList.super.immutableCopy();
    }

    @Override
    public MutableList<T> mutableCopy() {
        return WrapsList.super.mutableCopy();
    }

    protected java.util.List<T> nativeTail() {
        return delegate.subList(1, delegate.size());
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof List
                && List.elementsEqual(this, (List) object);
    }

    @Override
    public int hashCode() {
        return List.hashCode(this);
    }

}
