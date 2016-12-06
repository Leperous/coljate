package net.coljate.list.impl;

import java.util.NoSuchElementException;

import net.coljate.list.AbstractList;
import net.coljate.list.ImmutableArray;
import net.coljate.list.ImmutableListIterator;

/**
 *
 * @author ollie
 */
public class ImmutableSingletonArray<T>
        extends AbstractList<T>
        implements ImmutableArray<T> {

    public static <T> ImmutableSingletonArray<T> of(final T element) {
        return new ImmutableSingletonArray<>(element);
    }

    private final T element;

    protected ImmutableSingletonArray(final T element) {
        this.element = element;
    }

    @Override
    public int length() {
        return 1;
    }

    @Override
    public T get(final int index) {
        if (index != 0) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public T first() {
        return element;
    }

    @Override
    public T last() {
        return element;
    }

    @Override
    public java.util.ArrayList<T> mutableJavaCopy() {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(1);
        list.add(element);
        return list;
    }

    @Override
    public ImmutableListIterator<T> iterator() {
        return new ImmutableListIterator<T>() {

            private boolean exhausted;

            @Override
            public boolean hasPrevious() {
                return exhausted;
            }

            @Override
            public T previous() {
                if (!exhausted) {
                    throw new NoSuchElementException();
                }
                exhausted = false;
                return element;
            }

            @Override
            public boolean hasNext() {
                return !exhausted;
            }

            @Override
            public T next() {
                if (exhausted) {
                    throw new NoSuchElementException();
                }
                exhausted = true;
                return element;
            }

        };

    }

}
