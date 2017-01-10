package net.coljate;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface MutableIterableExtension<T> extends Iterable<T> {

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

    default boolean removeAll(@Nonnull final Iterable<?> elements) {
        boolean removed = false;
        for (final Object element : elements) {
            removed |= this.removeAll(element);
        }
        return removed;
    }

    default int removeWhere(@Nonnull final Predicate<? super T> predicate) {
        int removed = 0;
        for (final Iterator<T> iterator = this.iterator(); iterator.hasNext();) {
            if (predicate.test(iterator.next())) {
                iterator.remove();
                removed++;
            }
        }
        return removed;
    }

    default void clear() {
        for (final Iterator<?> iterator = this.iterator(); iterator.hasNext();) {
            iterator.next();
            iterator.remove();
        }
    }

}
