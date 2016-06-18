package net.ollie.coljate.list;

import net.ollie.coljate.list.mixin.WrapsLinkedList;

/**
 *
 * @author Ollie
 */
public class MutableWrappedLinkedList<T>
        extends MutableWrappedList<T>
        implements WrapsLinkedList<T> {

    private final java.util.LinkedList<T> delegate;

    MutableWrappedLinkedList(final java.util.LinkedList<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.LinkedList<T> delegate() {
        return delegate;
    }

    @Override
    public java.util.LinkedList<T> copyDelegate() {
        return WrapsLinkedList.copyIntoLinkedList(delegate);
    }

}
