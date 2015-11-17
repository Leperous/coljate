package net.ollie.coljate;

import java.util.Iterator;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.lists.ImmutableWrappedArrayList;
import net.ollie.coljate.lists.MutableWrappedArrayList;

/**
 *
 * @author Ollie
 */
public class WrappedCollection<T> implements Collection<T> {

    private final java.util.Collection<T> delegate;

    public WrappedCollection(final java.util.Collection<T> delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Collection<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public MutableCollection<T> mutableCopy() {
        return MutableWrappedArrayList.copyOf(delegate);
    }

    @Override
    public ImmutableCollection<T> immutableCopy() {
        return ImmutableWrappedArrayList.copyOf(delegate);
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
    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean contains(final Object object) {
        return delegate.contains(object);
    }

}
