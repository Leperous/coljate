package net.ollie.coljate.list.mixin;

import java.util.Collections;

import net.ollie.coljate.WrapsMutableCollection;
import net.ollie.coljate.list.MutableList;

/**
 * Some {@link MutableList} that wraps a {@link java.util.List}.
 *
 * @author Ollie
 */
public interface WrapsMutableList<T>
        extends WrapsMutableCollection<T>, MutableList<T>, WrapsList<T> {

    @Override
    java.util.List<T> delegate();

    @Override
    default java.util.List<T> copyDelegate() {
        return new java.util.ArrayList<>(this.delegate());
    }

    @Override
    @Deprecated
    default boolean add(T element) {
        return MutableList.super.add(element);
    }

    @Override
    default T set(int index, T element) {
        return this.delegate().set(index, element);
    }

    @Override
    default void prefix(final T element) {
        this.delegate().add(0, element);
    }

    @Override
    default void suffix(final T element) {
        this.delegate().add(element);
    }

    @Override
    default boolean removeAll(final Object element) {
        return this.delegate().removeAll(Collections.singletonList(element));
    }

}