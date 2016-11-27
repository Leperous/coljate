package net.coljate;

import net.coljate.feature.IterableExtension;
import net.coljate.feature.StreamExtension;

/**
 * Some {@link Iterable} {@link Container} with a {@link #count count} of
 * elements.
 *
 * @author ollie
 */
public interface Collection<T> extends IterableExtension<T>, StreamExtension<T> {

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

    /**
     *
     * @param array
     * @return an array containing all the elements in this collection. This
     * will either be the original array if it has sufficient capacity, or a new
     * array.
     * @see java.util.Collection#toArray(T[])
     */
    default T[] arrayCopy(final T[] array) {
        final int c = this.count();
        @SuppressWarnings("unchecked") //Same as java.util.AbstractCollection
        final T[] into = c > array.length
                ? (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), c)
                : array;
        int index = 0;
        for (final T element : this) {
            into[index++] = element;
        }
        return into;
    }

    static <T> Collection<T> viewOf(final java.util.Collection<T> collection) {
        return WrappedCollection.viewOf(collection);
    }

}
