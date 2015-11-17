package net.ollie.coljate.lists;

import net.ollie.coljate.lists.mixin.WrappedLinkedList;

import java.util.LinkedList;

import static net.ollie.coljate.lists.mixin.WrappedLinkedList.copyIntoLinkedList;
import net.ollie.coljate.lists.mixin.GenericMutableWrappedList;

/**
 *
 * @author Ollie
 */
public class MutableWrappedLinkedList<T> extends WrappedList<T> implements GenericMutableWrappedList<T>, WrappedLinkedList<T> {

    private final java.util.LinkedList<T> delegate;

    MutableWrappedLinkedList(final java.util.LinkedList<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.List<T> delegate() {
        return delegate;
    }

    @Override
    public java.util.LinkedList<T> copyDelegate() {
        return copyIntoLinkedList(delegate);
    }

    @Override
    public MutableList<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
