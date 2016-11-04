package net.coljate;

import java.util.Objects;

/**
 *
 * @author ollie
 */
public interface Collection<T> extends Container, Iterable<T> {

    /**
     *
     * @return the number of (null or non-null) elements considered to be
     * contained within this collection.
     */
    int count();

    /**
     *
     * @return a mutable copy of this collection.
     */
    MutableCollection<T> mutableCopy();

    /**
     *
     * @return an immutable copy of this collection.
     */
    ImmutableCollection<T> immutableCopy();

    /**
     *
     * @return a copy of this collection.
     * @deprecated only useful for bridging.
     */
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
