package net.coljate.counter;

import net.coljate.collection.MutableCollection;
import net.coljate.counter.impl.MutableHashCounter;

/**
 *
 * @author ollie
 */
public interface MutableCounter<T>
        extends Counter<T>, MutableCollection<T> {

    int increment(T element, int amount);

    default int increment(final T element) {
        return this.increment(element, 1);
    }

    default int incrementIfPresent(final Object element) {
        return this.contains(element)
                ? this.increment((T) element)
                : 0;
    }

    int decrement(T element, int amount);

    default int decrement(final T element) {
        return this.decrement(element, 1);
    }

    @Override
    default boolean removeFirst(final Object element) {
        final int count = this.count(element);
        if (count > 0) {
            this.decrement((T) element);
            return true;
        } else {
            return false;
        }
    }

    @Override
    default boolean removeAll(final Object element) {
        final int count = this.count(element);
        if (count > 0) {
            this.decrement((T) element, count);
            return true;
        } else {
            return false;
        }
    }

    static <T> MutableCounter<T> copyOf(final Iterable<? extends T> counter) {
        return MutableHashCounter.copyOf(counter);
    }

    static <T> MutableCounter<T> copyOf(final Counter<? extends T> counter) {
        return MutableHashCounter.copyOf(counter);
    }

}
