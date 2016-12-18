package net.coljate.counter;

import net.coljate.counter.impl.ConcurrentHashCounter;
import net.coljate.set.ConcurrentSet;

/**
 *
 * @author ollie
 */
public interface ConcurrentCounter<T>
        extends MutableCounter<T>, ConcurrentSet<T> {

    @Override
    ConcurrentCounter<T> mutableCopy();

    static <T> ConcurrentCounter<T> create() {
        return ConcurrentHashCounter.create();
    }

}
