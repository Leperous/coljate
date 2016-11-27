package net.coljate.set;

import net.coljate.MutableCollection;

/**
 *
 * @author ollie
 */
public interface MutableSet<T> extends Set<T>, MutableCollection<T> {

    /**
     *
     * @param element
     * @return true if the element was added because it was not previously
     * contained by this set.
     */
    boolean add(T element);

    boolean addAll(Iterable<? extends T> elements);

    @Deprecated
    default boolean removeFirst(final Object element) {
        return this.removeAll(element);
    }

}
