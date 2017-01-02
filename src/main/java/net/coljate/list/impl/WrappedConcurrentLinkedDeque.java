package net.coljate.list.impl;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.OptionalInt;
import java.util.concurrent.ConcurrentLinkedDeque;

import net.coljate.list.ConcurrentList;
import net.coljate.util.Functions;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 *
 * @author ollie
 * @see ConcurrentLinkedDeque
 */
public class WrappedConcurrentLinkedDeque<@NonNull T>
        extends WrappedDeque<T>
        implements ConcurrentList<T> {

    protected WrappedConcurrentLinkedDeque(final java.util.concurrent.ConcurrentLinkedDeque<T> deque) {
        super(deque, OptionalInt.empty());
    }

    @Override
    public Element<T> poll() {
        return Functions.ifNonNull(this.nativePoll(), Element::of);
    }

    @Override
    public Element<T> peek() {
        return Functions.ifNonNull(this.nativePeek(), Element::of);
    }

    @Override
    public WrappedConcurrentLinkedDeque<T> mutableCopy() {
        return new WrappedConcurrentLinkedDeque<>(this.mutableJavaCopy(i -> new ConcurrentLinkedDeque<>()));
    }

    public static class ConcurrentLinkedDequeListBridge<T>
            extends java.util.concurrent.ConcurrentLinkedDeque<T>
            implements java.util.List<T> {

        private static final long serialVersionUID = 1L;

        @Override
        public boolean addAll(int index, Collection<? extends T> c) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public T get(final int index) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public T set(final int index, final T element) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void add(int index, T element) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public T remove(int index) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int indexOf(Object o) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int lastIndexOf(Object o) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ListIterator<T> listIterator() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ListIterator<T> listIterator(int index) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public List<T> subList(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
