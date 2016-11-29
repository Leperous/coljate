package net.coljate.collection;

import java.util.Iterator;
import java.util.Spliterator;

import net.coljate.UnmodifiableIterator;
import net.coljate.util.Equality;

/**
 *
 * @author ollie
 */
public class WrappedCollection<T> implements Collection<T> {

    public static <T> Collection<T> viewOf(final java.util.Collection<T> collection) {
        return new WrappedCollection<>(collection);
    }

    private final java.util.Collection<T> delegate;

    protected WrappedCollection(final java.util.Collection<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean contains(final Object object) {
        return delegate.contains(object);
    }

    @Override
    public int count() {
        return delegate.size();
    }

    @Override
    public Iterator<T> iterator() {
        return UnmodifiableIterator.wrap(delegate.iterator());
    }

    protected java.util.Collection<T> mutableDelegateCopy() {
        return new java.util.ArrayList<>(delegate);
    }

    @Override
    public MutableCollection<T> mutableCopy() {
        return new MutableWrappedCollection<>(this.mutableDelegateCopy());
    }

    @Override
    public ImmutableCollection<T> immutableCopy() {
        return ImmutableWrappedCollection.copyOf(delegate);
    }

    @Override
    public Spliterator<T> spliterator() {
        return delegate.spliterator();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Collection
                && Equality.orderedEquals(this, (Collection) obj);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException(); //TODO
    }

}
