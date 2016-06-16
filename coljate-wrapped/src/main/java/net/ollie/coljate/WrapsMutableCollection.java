package net.ollie.coljate;

import java.util.Collections;

import javax.annotation.Nonnull;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface WrapsMutableCollection<@Nullable T> extends MutableCollection<T> {

    @Nonnull
    java.util.Collection<T> delegate();

    @Override
    default boolean add(final T element) {
        return this.delegate().add(element);
    }

    @Override
    default boolean addAll(final Iterable<? extends T> iterable) {
        return iterable instanceof java.util.Collection
                ? this.delegate().addAll((java.util.Collection<? extends T>) iterable)
                : MutableCollection.super.addAll(iterable);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    default boolean removeOnce(final Object element) {
        return this.delegate().remove(element);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    default boolean removeAll(final Object element) {
        return this.delegate().removeAll(Collections.singleton(element));
    }

    @Override
    default boolean removeAll(final Iterable<? extends T> iterable) {
        return iterable instanceof java.util.Collection
                ? this.delegate().removeAll((java.util.Collection<? extends T>) iterable)
                : MutableCollection.super.removeAll(iterable);
    }

    @Override
    default void clear() {
        this.delegate().clear();
    }

}
