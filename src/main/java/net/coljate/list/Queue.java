package net.coljate.list;

import java.util.NoSuchElementException;

import net.coljate.collection.MutableCollection;
import net.coljate.feature.Ordered;

/**
 * A queue is a mutable, ordered collection, accessed through adding or removing
 * some head element.
 *
 * @author ollie
 * @see java.util.Queue
 */
public interface Queue<T> extends Ordered<T>, MutableCollection<T> {

    T head();

    T poll();

    default T remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.poll();
    }

    @Override
    @Deprecated
    default T first() {
        return this.head();
    }

    @Override
    Queue<T> mutableCopy();

    @Override
    ImmutableList<T> immutableCopy();

}
