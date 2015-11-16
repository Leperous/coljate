package net.ollie.coljate.utils.iterators;

import java.util.NoSuchElementException;

/**
 *
 * @author Ollie
 */
class SingletonIterator<V> implements UnmodifiableIterator<V> {

    private boolean hasNext = true;
    private final V element;

    SingletonIterator(final V element) {
        this.element = element;
    }

    V element() {
        return element;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public V next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        hasNext = false;
        return element;
    }

}
