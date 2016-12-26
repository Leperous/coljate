package net.coljate.collection;

import java.util.Iterator;
import java.util.Objects;

import net.coljate.collection.impl.MutableWrappedCollection;
import net.coljate.list.MutableList;
import net.coljate.list.impl.MutableWrappedList;

/**
 *
 * @author ollie
 */
public interface MutableCollection<T> extends Collection<T> {

    void clear();

    default boolean removeFirst(final Object element) {
        for (final Iterator<T> iterator = this.iterator(); iterator.hasNext();) {
            if (Objects.equals(iterator.next(), element)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    default boolean removeAll(final Object element) {
        boolean removedAny = false;
        for (final Iterator<T> iterator = this.iterator(); iterator.hasNext();) {
            if (Objects.equals(iterator.next(), element)) {
                iterator.remove();
                removedAny = true;
            }
        }
        return removedAny;
    }

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

    static <T> MutableCollection<T> copyOf(final Collection<? extends T> collection) {
        return MutableList.copyOf(collection);
    }

}
