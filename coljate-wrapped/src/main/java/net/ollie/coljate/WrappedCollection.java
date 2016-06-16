package net.ollie.coljate;

import java.util.Iterator;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.list.ImmutableWrappedArrayList;
import net.ollie.coljate.list.MutableWrappedArrayList;

/**
 *
 * @author Ollie
 */
public class WrappedCollection<T>
        extends AbstractCollection<T>
        implements Collection<T> {

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
    public int count() {
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

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof WrappedCollection
                && this.delegate.equals(((WrappedCollection) obj).delegate);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

}
