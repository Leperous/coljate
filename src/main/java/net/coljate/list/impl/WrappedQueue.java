package net.coljate.list.impl;

import java.util.ArrayDeque;
import java.util.Optional;
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
    public Optional<T> peek() {
        return Optional.ofNullable(queue.peek());
    }

    @Override
    public Optional<T> poll() {
        return Optional.ofNullable(queue.poll());
    }

    @Override
    public OptionalInt capacity() {
        return capacity;
    }

    @Override
    @Deprecated
    public T remove() {
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

}
