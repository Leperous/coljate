package net.coljate.list;

import java.util.NoSuchElementException;
import java.util.OptionalInt;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.coljate.collection.MutableCollection;
import net.coljate.list.impl.ImmutableNativeArray;
import net.coljate.util.Functions;

/**
 * A queue is a mutable, ordered collection, accessed through adding or removing some head element.
 *
 * It may or may not have a {@link #capacity finite capacity}.
 *
 * @author ollie
 * @see java.util.Queue
 */
public interface Queue<T> extends Ordered<T>, MutableCollection<T> {

    /**
     * @return the head of this queue, or null if it is empty.
     */
    @CheckForNull
    Element<T> peek();

    /**
     * @return the (detached) head of this queue, or null if it is empty.
     */
    @CheckForNull
    Element<T> poll();

    /**
     * @return the head of this queue (which may be null, if this queue permits).
     * @see java.util.Queue#element
     * @throws NoSuchElementException if this queue is empty.
     */
    @Deprecated
    @CheckForNull
    default T element() throws NoSuchElementException {
        final Element<T> result = this.peek();
        if (result == null) {
            throw new NoSuchElementException("Queue is empty!");
        } else {
            return result.value();
        }
    }

    @Override
    default T first() {
        return Functions.ifNonNull(this.peek(), Element::value);
    }

    @Nonnull
    OptionalInt capacity();

    default boolean isFull() {
        final OptionalInt capacity = this.capacity();
        return capacity.isPresent()
                ? capacity.getAsInt() <= this.count()
                : false;
    }

    /**
     * @return true if the element could be added to this queue, or false if it could not, for example because it is
     * full or does not permit null elements.
     */
    boolean add(T element);

    /**
     *
     * @param element
     * @throws EnqueueException if the element could not be added.
     */
    default void enqueue(final T element) throws EnqueueException {
        if (!this.add(element)) {
            throw new EnqueueException("Failed to enqueue [" + element + "]!");
        }
    }

    /**
     *
     * @return the head.
     * @throws NoSuchElementException if this queue is empty.
     */
    default T dequeue() throws NoSuchElementException {
        final Element<T> result = this.poll();
        if (result == null) {
            throw new NoSuchElementException("Queue is empty!");
        } else {
            return result.value();
        }
    }

    @Override
    Queue<T> mutableCopy();

    @Override
    default ImmutableList<T> immutableCopy() {
        return ImmutableNativeArray.copyOf(this);
    }

    interface Element<@Nullable T> {

        T value();

        static <T> Element<T> of(final T value) {
            return () -> value;
        }

    }

    class EnqueueException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public EnqueueException(final String message) {
            super(message);
        }

    }

    class QueueFullException extends EnqueueException {

        private static final long serialVersionUID = 1L;

        public QueueFullException(final String message) {
            super(message);
        }

    }

}
