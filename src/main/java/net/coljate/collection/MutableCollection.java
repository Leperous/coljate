package net.coljate.collection;

import net.coljate.collection.impl.MutableWrappedCollection;
import net.coljate.list.impl.MutableWrappedList;

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

    static <T> MutableCollection<T> viewOf(final java.util.Collection<T> collection) {
        return MutableWrappedCollection.viewOf(collection);
    }

    @SafeVarargs
    static <T> MutableCollection<T> copyOf(final T... elements) {
        return MutableWrappedList.createArrayList(elements);
    }

}
