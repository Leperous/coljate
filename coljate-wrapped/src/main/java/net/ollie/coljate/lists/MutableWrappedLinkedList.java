package net.ollie.coljate.lists;

import net.ollie.coljate.lists.mixin.GenericMutableWrappedList;

/**
 *
 * @author Ollie
 */
public class MutableWrappedLinkedList<T> extends WrappedLinkedList<T> implements GenericMutableWrappedList<T> {

    MutableWrappedLinkedList(final java.util.LinkedList<T> delegate) {
        super(delegate);
    }

    @Override
    public java.util.List<T> delegate() {
        return delegate;
    }

    @Override
    public MutableList<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
