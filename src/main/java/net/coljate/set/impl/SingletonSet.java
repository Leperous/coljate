package net.coljate.set.impl;

import java.util.NoSuchElementException;
import java.util.Objects;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.ImmutableCollection;
import net.coljate.set.AbstractSet;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public class SingletonSet<T>
        extends AbstractSet<T>
        implements ImmutableSet<T> {

    private final T element;

    public SingletonSet(final T element) {
        this.element = element;
    }

    @Override
    public boolean contains(final Object object) {
        return Objects.equals(object, element);
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
    public ImmutableCollection<T> with(final T element) {
        return MutableWrappedHashSet.copyOf(this.element, element).immutableCopy(); //FIXME
    }

}
