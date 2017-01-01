package net.coljate.list;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;

import net.coljate.collection.MutableCollection;
import net.coljate.list.impl.ImmutableNativeArray;

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
     * @return the head of this queue.
     */
    Optional<T> peek();

    /**
     * @return the (detached) head of this queue.
     */
    Optional<T> poll();

    @Deprecated
    default T element() {
        return this.peek().orElseThrow(() -> new NoSuchElementException());
    }

    @Deprecated
    default T remove() {
        return this.poll().orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    default T first() {
        return this.peek().orElse(null);
    }

    @Nonnull
    OptionalInt capacity();

    default boolean hasCapacity() {
        return this.capacity().orElse(Integer.MAX_VALUE) - this.count() > 0;
    }

    /**
     * @return true if the element could be added, or false if not, e.g. because of capacity restrictions.
     */
    boolean add(T element);

    @Override
    Queue<T> mutableCopy();

    @Override
    default ImmutableList<T> immutableCopy() {
        return ImmutableNativeArray.copyOf(this);
    }

}
