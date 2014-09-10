package net.ollie.sc4j.utils.iterators;

import java.util.NoSuchElementException;

/**
 *
 * @author Ollie
 */
class EmptyIterator<V> implements UnmodifiableIterator<V> {

    static final EmptyIterator INSTANCE = new EmptyIterator();

    EmptyIterator() {
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public V next() {
        throw new NoSuchElementException();
    }

}
