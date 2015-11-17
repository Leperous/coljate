package net.ollie.coljate.lists;

import net.ollie.coljate.lists.mixin.MutableNativeListMixin;

/**
 *
 * @author Ollie
 */
public class MutableLinkedList<T> extends NativeLinkedList<T> implements MutableNativeListMixin<T> {

    MutableLinkedList(final java.util.LinkedList<T> delegate) {
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
