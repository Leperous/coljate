package net.coljate.list.impl;

import java.util.concurrent.atomic.AtomicReferenceArray;

import net.coljate.list.ImmutableArray;
import net.coljate.list.ListIterator;
import net.coljate.list.MutableArray;

/**
 *
 * @author ollie
 */
public class MutableAtomicArray<T> implements MutableArray<T> {

    protected MutableAtomicArray(final AtomicReferenceArray<T> array) {
    }

    @Override
    public T get(final int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int length() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableArray<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void prefix(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void suffix(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeFirst(Object element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Object element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

}
