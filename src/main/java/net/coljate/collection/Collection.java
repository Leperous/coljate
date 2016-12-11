package net.coljate.collection;

import java.util.function.Function;
import java.util.function.IntFunction;

import net.coljate.Container;
import net.coljate.collection.impl.WrappedCollection;
import net.coljate.collection.lazy.LazyCollection;
import net.coljate.feature.IterableExtension;
import net.coljate.feature.StreamExtension;
import net.coljate.set.Set;

/**
 * Some {@link Iterable} {@link Container} with a {@link #count count} of elements.
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
     * @return a mutable copy of this collection.
     */
    default java.util.Collection<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.ArrayList::new);
    }

    default <C extends java.util.Collection<? super T>> C mutableJavaCopy(final IntFunction<? extends C> createCollection) {
        final C collection = createCollection.apply(this.count());
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
     * @return an array containing all the elements in this collection. This will either be the original array if it has
     * sufficient capacity, or a new array.
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

    /**
     * Lazily translate this collection.
     *
     * @param <L>
     * @param intoLazy
     * @return
     */
    default <L extends LazyCollection<?>> L lazily(final Function<Collection<T>, L> intoLazy) {
        return intoLazy.apply(this);
    }

    static <T> Collection<T> of(final T element) {
        return Set.of(element);
    }

    static <T> Collection<T> viewOf(final java.util.Collection<T> collection) {
        return WrappedCollection.viewOf(collection);
    }

}
