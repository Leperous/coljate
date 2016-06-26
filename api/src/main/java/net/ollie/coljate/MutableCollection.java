package net.ollie.coljate;

import java.util.Iterator;
import java.util.Objects;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 * @param <T>
 */
public interface MutableCollection<@Nullable T> extends Collection<T> {

    boolean add(T element);

    default boolean addAll(final Iterable<? extends T> iterable) {
        boolean added = false;
        for (final T element : iterable) {
            added |= this.add(element);
        }
        return added;
    }

    /**
     * Remove a single object from this collection.
     *
     * @param element
     * @return
     */
    default boolean removeOnce(final Object element) {
        return this.remove(element, 1);
    }

    default boolean remove(final Object element, final int maxTimes) {
        int numRemoved = 0;
        for (final Iterator<T> iterator = this.iterator(); iterator.hasNext();) {
            if (Objects.equals(element, iterator.next())) {
                iterator.remove();
                if (++numRemoved == maxTimes) {
                    return true;
                }
            }
        }
        return numRemoved > 0;
    }

    /**
     * Remove all objects that {@link Object#equals equal} the given object from this collection.
     *
     * @param element
     * @return true if at least one instance was removed.
     */
    default boolean removeAll(final Object element) {
        boolean removed = false;
        for (final Iterator<T> iterator = this.iterator(); iterator.hasNext();) {
            if (Objects.equals(element, iterator.next())) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

    default boolean removeAll(final Iterable<? extends T> iterable) {
        boolean removed = false;
        for (final T element : iterable) {
            removed |= this.removeAll(element);
        }
        return removed;
    }

    void clear();

}
