package net.coljate.map;

import net.coljate.set.MutableSet;

/**
 *
 * @author ollie
 */
public interface MutableCounter<T>
        extends Counter<T>, MutableSet<T> {

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
    @Deprecated
    default boolean remove(final Object element) {
        return this.removeFirst(element);
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

}
