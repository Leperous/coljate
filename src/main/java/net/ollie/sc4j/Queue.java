package net.ollie.sc4j;

import java.util.NoSuchElementException;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * A mutable sequence where the head is m
 *
 * @author Ollie
 * @see java.util.Queue
 */
public interface Queue<V>
        extends Sequence.Mutable<V> {

    boolean offer(V value);

    @CheckForNull
    V poll();

    @CheckForNull
    V peek();

    @Nonnull
    V element() throws NoSuchElementException;

    @Nonnull
    V remove() throws NoSuchElementException;

    @Override
    Queue<V> tail();

    @Nonnull
    Sequence<V> drain();

    @Override
    Queue<V> mutable();

}
