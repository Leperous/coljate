package net.coljate.list.impl;

import net.coljate.list.ImmutableList;

/**
 *
 * @author ollie
 */
public class ImmutableSingletonList<T> implements ImmutableList<T> {

    private final T element;

    public ImmutableSingletonList(final T element) {
        this.element = element;
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
    public ImmutableListIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
