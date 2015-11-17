package net.ollie.coljate.lists;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.MutableCollection;

/**
 *
 * @author Ollie
 */
public interface MutableList<T> extends List<T>, MutableCollection<T> {

    @Nullable
    T set(int index, T element);

    boolean removeAll(Object element);

    @Override
    MutableList<T> tail();

}
