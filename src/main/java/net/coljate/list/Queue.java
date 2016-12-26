package net.coljate.list;

import java.util.NoSuchElementException;
import java.util.Optional;

import net.coljate.collection.MutableCollection;

/**
 * A queue is a mutable, ordered collection, accessed through adding or removing some head element.
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

    @Override
    Queue<T> mutableCopy();

    @Override
    ImmutableList<T> immutableCopy();

}
