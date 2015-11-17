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

    boolean remove(Object element);

    default boolean removeAll(final Iterable<? extends T> iterable) {
        boolean removed = false;
        for (final T element : iterable) {
            removed |= this.remove(element);
        }
        return removed;
    }

    boolean clear();

}
