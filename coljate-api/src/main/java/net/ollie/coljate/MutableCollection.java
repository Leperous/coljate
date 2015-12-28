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
        for (final Iterator<T> iterator = this.iterator(); iterator.hasNext();) {
            if (Objects.equals(element, iterator.next())) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Remove all instances from this collection.
     *
     * @param element
     * @return
     */
    default boolean removeAll(Object element) {
        boolean removed = false;
        while (this.removeOnce(element)) {
            removed = true;
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
