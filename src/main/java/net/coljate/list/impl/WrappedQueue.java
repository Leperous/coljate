package net.coljate.list.impl;

import java.util.ArrayDeque;
import java.util.OptionalInt;

import net.coljate.collection.impl.MutableWrappedCollection;
import net.coljate.list.ImmutableList;
import net.coljate.list.Queue;

/**
 *
 * @author ollie
 */
public class WrappedQueue<T>
        extends MutableWrappedCollection<T>
        implements Queue<T> {

    private final java.util.Queue<T> queue;
    private final OptionalInt capacity;

    protected WrappedQueue(final java.util.Queue<T> queue, final OptionalInt capacity) {
        super(queue);
        this.queue = queue;
        this.capacity = capacity;
    }

    @Override
    public boolean add(final T element) {
        return queue.offer(element);
    }

    @Override
    public Element<T> peek() {
        return queue.isEmpty()
                ? null
                : new SimpleElement<>(queue.peek());
    }

    @Override
    public Element<T> poll() {
        return queue.isEmpty()
                ? null
                : new SimpleElement<>(queue.poll());
    }

    @Override
    public OptionalInt capacity() {
        return capacity;
    }

    @Override
    @Deprecated
    public T dequeue() {
        return queue.remove();
    }

    @Override
    @Deprecated
    public T element() {
        return queue.element();
    }

    @Override
    public WrappedQueue<T> mutableCopy() {
        return new WrappedQueue<>(new ArrayDeque<>(queue), OptionalInt.empty());
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        return Queue.super.immutableCopy();
    }

    private static final class SimpleElement<T> implements Element<T> {

        private final T value;

        SimpleElement(final T value) {
            this.value = value;
        }

        @Override
        public T value() {
            return value;
        }

    }

}
