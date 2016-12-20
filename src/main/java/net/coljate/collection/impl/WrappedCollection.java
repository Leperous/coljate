package net.coljate.collection.impl;

import java.util.Iterator;
import java.util.Spliterator;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.Collection;
import net.coljate.collection.ImmutableCollection;
import net.coljate.collection.MutableCollection;
import net.coljate.util.Equality;

/**
 *
 * @author ollie
 */
public class WrappedCollection<T> implements Collection<T> {

    private final java.util.Collection<? extends T> delegate;

    public WrappedCollection(final java.util.Collection<? extends T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object[] arrayCopy() {
        return delegate.toArray();
    }

    @Override
    public java.util.Collection<T> mutableJavaCopy() {
        return new java.util.ArrayList<>(delegate);
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

    @Override
    public MutableCollection<T> mutableCopy() {
        return new MutableWrappedCollection<>(this.mutableJavaCopy());
    }

    @Override
    public ImmutableCollection<T> immutableCopy() {
        return new ImmutableWrappedCollection<>(this.mutableJavaCopy());
    }

    @Override
    @SuppressWarnings("unchecked") //Read-only
    public Spliterator<T> spliterator() {
        return (Spliterator<T>) delegate.spliterator();
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
