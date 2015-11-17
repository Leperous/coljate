package net.ollie.coljate.lists;

import static java.util.Objects.requireNonNull;
import java.util.OptionalInt;

import net.ollie.coljate.WrappedCollection;
import net.ollie.coljate.lists.mixin.WrapsList;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Ollie
 */
public class WrappedList<T> extends WrappedCollection<T> implements WrapsList<T> {

    private final java.util.List<T> delegate;

    public WrappedList(final java.util.List<T> delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public java.util.List<T> copyDelegate() {
        return new java.util.ArrayList<>(delegate);
    }

    @Override
    public T get(int index) {
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

    protected java.util.List<T> nativeTail() {
        return delegate.subList(1, delegate.size());
    }

    @Override
    public MutableList<T> mutableCopy() {
        return MutableWrappedArrayList.copyOf(this);
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        return ImmutableWrappedArrayList.copyOf(this);
    }

}
