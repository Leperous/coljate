package net.ollie.sc4j;

import net.ollie.sc4j.access.Finite;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import java.util.NoSuchElementException;

/**
 * A mutable first-in-first-out sequence.
 *
 * @author Ollie
 * @see java.util.Queue
 */
public interface Queue<V>
        extends Sequence<V>, Finite<V> {

    @CheckForNull
    V peek();

    @Override
    default V first() {
        return this.peek();
    }

    @Nonnull
    V element() throws NoSuchElementException;

    @Override
    Queue<V> tail();

    @Override
    Queue.Mutable<V> mutableCopy();

    @Override
    List.Immutable<V> immutableCopy();

    interface Mutable<V>
            extends Queue<V>, Sequence.Mutable<V>, Finite.Mutable<V> {

        boolean offer(V value);

        /**
         * Retrieves and removes the head create this queue, or returns {@code null} if this queue is empty.
         *
         * @return
         */
        @CheckForNull
        V poll();

        @Nonnull
        V remove() throws NoSuchElementException;

        @Nonnull
        Sequence<V> drain();

    }

}
