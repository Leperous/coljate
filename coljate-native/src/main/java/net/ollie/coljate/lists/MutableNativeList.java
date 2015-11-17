package net.ollie.coljate.lists;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
interface MutableNativeList<T> extends MutableList<T> {

    @Nonnull
    java.util.List<T> delegate();

    @Override
    default T set(int index, T element) {
        return this.delegate().set(index, element);
    }

    @Override
    default boolean add(final T element) {
        return this.delegate().add(element);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    default boolean remove(final Object element) {
        return this.delegate().remove(element);
    }

}
