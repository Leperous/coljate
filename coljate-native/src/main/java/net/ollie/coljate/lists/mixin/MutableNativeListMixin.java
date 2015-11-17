package net.ollie.coljate.lists.mixin;

import javax.annotation.Nonnull;

import net.ollie.coljate.lists.MutableList;

/**
 *
 * @author Ollie
 */
public interface MutableNativeListMixin<T> extends MutableList<T>, NativeListMixin<T> {

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
