package net.coljate.list;

import java.util.NoSuchElementException;
import java.util.OptionalInt;

import javax.annotation.Nonnull;

import net.coljate.collection.MutableCollection;
import net.coljate.list.impl.ImmutableNativeArray;
import net.coljate.util.Functions;

/**
 * A queue is a mutable, ordered collection, accessed through adding or removing some head element.
 *
 * It may or may not have a {@link #capacity}.
 *
 * @author ollie
 * @see java.util.Queue
 */
public interface Queue<T> extends Ordered<T>, MutableCollection<T> {

    /**
     * @return the head of this queue, or null if it is empty.
     */
    Element<T> peek();

    /**
     * @return the (detached) head of this queue, or null if it is empty.
     */
    Element<T> poll();

    @Deprecated
    default T element() {
        final Element<T> result = this.peek();
        if (result == null) {
            throw new NoSuchElementException();
        } else {
            return result.value();
        }
    }

    @Override
    @Deprecated
    default T first() {
        return Functions.ifNonNull(this.peek(), Element::value);
    }

    @Nonnull
    OptionalInt capacity();

    default boolean hasCapacity() {
        final OptionalInt capacity = this.capacity();
        return capacity.isPresent()
                ? capacity.getAsInt() - this.count() > 0
                : true;
    }

    default boolean isFull() {
        return !this.hasCapacity();
    }

    /**
     * @return true if the element could be added to this queue, or false if not, for example because of capacity
     * restrictions.
     */
    boolean add(T element);

    default void enqueue(final T element) throws QueueFullException {
        if (!this.add(element)) {
            throw new QueueFullException();
        }
    }

    default T dequeue() throws NoSuchElementException {
        final Element<T> result = this.poll();
        if (result == null) {
            throw new NoSuchElementException();
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

    interface Element<T> {

        T value();

    }

    class QueueFullException extends RuntimeException {

    }

}
