package net.coljate.list.impl;

import java.util.Iterator;
import java.util.OptionalInt;

import net.coljate.list.Queue;
import net.coljate.util.Arrays;

/**
 *
 * @author ollie
 */
public class CircularBuffer<T> implements Queue<T> {

    private final Element<T>[] array;
    private int read, write;

    protected CircularBuffer(final Element<T>[] array) {
        this.array = array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Element<T> peek() {
        return this.isEmpty()
                ? null
                : array[read];
    }

    @Override
    public Element<T> poll() {
        return this.isEmpty()
                ? null
                : array[this.nextRead()];
    }

    private int nextRead() {
        return (read++) % array.length;
    }

    @Override
    public OptionalInt capacity() {
        return OptionalInt.of(array.length);
    }

    @Override
    public boolean add(final T element) {
        if (this.isFull()) {
            return false;
        } else {
            array[this.nextWrite()] = new SimpleElement<>(element);
            return true;
        }
    }

    private int nextWrite() {
        return (write++) % array.length;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        read = write = 0;
        //Also drop references
        Arrays.update(array, e -> null);
    }

    @Override
    public boolean isEmpty() {
        return read == write;
    }

    @Override
    public boolean isFull() {
        return read == (write + 1) % array.length;
    }

    @Override
    public Queue<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static final class SimpleElement<T> implements Element<T> {

        private final T value;

        public SimpleElement(final T value) {
            this.value = value;
        }

        @Override
        public T value() {
            return value;
        }

    }

}
