package net.coljate.list.impl;

import net.coljate.list.ImmutableArray;
import net.coljate.list.MutableArray;

/**
 *
 * @author ollie
 */
public class EmptyArray<T> implements ImmutableArray<T> {

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public T get(final int index) {
        throw new IndexOutOfBoundsException("Empty");
    }

    @Override
    public ImmutableListIterator<T> iterator() {
        return ImmutableListIterator.empty();
    }

    @Override
    public MutableArray<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
