package net.coljate.list;

import java.util.NoSuchElementException;
import java.util.Optional;

import net.coljate.collection.MutableCollection;
import net.coljate.feature.Ordered;

/**
 *
 * @author ollie
 * @see java.util.Queue
 */
public interface Queue<T> extends Ordered<T>, MutableCollection<T> {

    T head();

    Optional<T> removeHead();

    default T poll() {
        return this.removeHead().orElse(null);
    }

    default T remove() {
        return this.removeHead().orElseThrow(() -> new NoSuchElementException());
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
