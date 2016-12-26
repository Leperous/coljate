package net.coljate.list.impl;

import net.coljate.list.ListIterator;
import net.coljate.list.MutableList;

/**
 *
 * @author ollie
 */
public class WrappedDeque<T>
        extends WrappedQueue<T>
        implements MutableList<T> {

    private final java.util.Deque<T> deque;

    public WrappedDeque(final java.util.Deque<T> deque) {
        super(deque);
        this.deque = deque;
    }

    @Override
    public ListIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T first() {
        return super.first();
    }

    @Override
    public T last() {
        return deque.peekLast();
    }

    @Override
    public void prefix(final T element) {
        deque.addFirst(element);
    }

    @Override
    public void suffix(final T element) {
        deque.addLast(element);
    }

    @Override
    public java.util.LinkedList<T> mutableJavaCopy() {
        return this.mutableJavaCopy(i -> new java.util.LinkedList<>());
    }

    @Override
    public WrappedDeque<T> mutableCopy() {
        return new WrappedDeque<>(this.mutableJavaCopy());
    }

}
