package net.coljate.list.impl;

import java.io.Serializable;

import net.coljate.list.AbstractArray;
import net.coljate.list.ImmutableArray;
import net.coljate.list.ImmutableListIterator;

/**
 *
 * @author ollie
 */
public class EmptyArray<T>
        extends AbstractArray<T>
        implements ImmutableArray<T>, Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    private static final EmptyArray INSTANCE = new EmptyArray();

    @SuppressWarnings("unchecked")
    public static <T> EmptyArray<T> instance() {
        return INSTANCE;
    }

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

}
