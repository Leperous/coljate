package net.ollie.coljate;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
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
    boolean removeOnce(Object element);

    /**
     * Remove all instances from this collection.
     *
     * @param element
     * @return
     */
    boolean removeAll(Object element);

    default boolean removeAll(final Iterable<? extends T> iterable) {
        boolean removed = false;
        for (final T element : iterable) {
            removed |= this.removeAll(element);
        }
        return removed;
    }

    boolean clear();

}
