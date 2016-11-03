package net.coljate;

import java.util.Objects;

/**
 *
 * @author ollie
 */
public interface Collection<T> extends Container, Iterable<T> {

    /**
     *
     * @return the number of elements in this collection.
     */
    int count();

    MutableCollection<T> mutableCopy();

    ImmutableCollection<T> immutableCopy();

    @Deprecated
    default java.util.Collection<T> javaCollectionCopy() {
        final java.util.Collection<T> collection = new java.util.ArrayList<>(this.count());
        this.forEach(collection::add);
        return collection;
    }

    @Override
    default boolean contains(final Object object) {
        for (final T element : this) {
            if (Objects.equals(object, element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    default boolean isEmpty() {
        return this.count() == 0;
    }

}
