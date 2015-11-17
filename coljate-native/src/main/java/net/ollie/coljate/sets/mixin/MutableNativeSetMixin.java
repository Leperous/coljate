package net.ollie.coljate.sets.mixin;

import net.ollie.coljate.sets.MutableSet;

/**
 *
 * @author Ollie
 */
public interface MutableNativeSetMixin<T> extends NativeSetMixin<T>, MutableSet<T> {

    java.util.Set<T> delegate();

    @Override
    default boolean add(final T element) {
        return this.delegate().add(element);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public default boolean remove(final Object element) {
        return this.delegate().remove(element);
    }

}
