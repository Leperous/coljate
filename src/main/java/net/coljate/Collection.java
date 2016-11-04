package net.coljate;

import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
    MutableCollection<? extends T> mutableCopy();

    /**
     *
     * @return an immutable copy of this collection.
     */
    ImmutableCollection<? extends T> immutableCopy();

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

    /**
     *
     * @return a new array containing the elements in this collection.
     */
    default Object[] arrayCopy() {
        final Object[] array = new Object[this.count()];
        int index = 0;
        for (final T element : this) {
            array[index++] = element;
        }
        return array;
    }

    default T[] arrayCopy(final T[] array) {
        final int max = Math.min(this.count(), array.length);
        int index = 0;
        for (final T element : this) {
            if (index >= max) {
                break;
            }
            array[index++] = element;
        }
        return array;
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

    default Stream<T> serialStream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    default Stream<T> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }

}
