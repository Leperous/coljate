package net.coljate.collection.impl;

import java.util.NoSuchElementException;
import java.util.Objects;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.ImmutableCollection;
import net.coljate.collection.MutableCollection;

/**
 *
 * @author ollie
 */
public class SingletonCollection<T>
        implements ImmutableCollection<T> {

    private final T element;

    protected SingletonCollection(final T element) {
        this.element = element;
    }

    public T element() {
        return element;
    }

    @Override
    public boolean contains(final Object object) {
        return Objects.equals(this.element(), object);
    }

    @Override
    public ImmutableCollection<T> with(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MutableCollection<? extends T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return new UnmodifiableIterator<T>() {

            boolean done = false;

            @Override
            public boolean hasNext() {
                return !done;
            }

            @Override
            public T next() {
                if (done) {
                    throw new NoSuchElementException();
                }
                done = true;
                return element;
            }

        };
    }

    @Override
    @Deprecated
    public SingletonCollection<T> immutableCopy() {
        return this;
    }

}
