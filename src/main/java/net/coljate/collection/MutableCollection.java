package net.coljate.collection;

/**
 *
 * @author ollie
 */
public interface MutableCollection<T> extends Collection<T> {

    void clear();
    
    boolean removeFirst(Object element);

    boolean removeAll(Object element);

    default boolean removeAll(final Iterable<?> elements) {
        boolean removed = false;
        for (final Object element : elements) {
            removed &= this.removeAll(element);
        }
        return removed;
    }

}
