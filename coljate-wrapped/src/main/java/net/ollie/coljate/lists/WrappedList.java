package net.ollie.coljate.lists;

import java.util.Iterator;
import static java.util.Objects.requireNonNull;
import java.util.OptionalInt;

/**
 *
 * @author Ollie
 */
public class WrappedList<T> implements List<T> {

    private final java.util.List<T> delegate;

    protected WrappedList(final java.util.List<T> delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean contains(final Object object) {
        return delegate.contains(object);
    }

    @Override
    public T get(int index) {
        return delegate.get(index);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public OptionalInt indexOf(final Object element) {
        final int index = delegate.indexOf(element);
        return index >= 0 ? OptionalInt.of(index) : OptionalInt.empty();
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
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
