package net.ollie.coljate.lists;

/**
 *
 * @author Ollie
 */
public class MutableLinkedList<T> extends NativeLinkedList<T> implements MutableNativeList<T> {

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
