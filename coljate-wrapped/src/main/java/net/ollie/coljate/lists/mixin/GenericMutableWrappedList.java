package net.ollie.coljate.lists.mixin;

import java.util.Collections;

import net.ollie.coljate.GenericMutableWrappedCollection;
import net.ollie.coljate.lists.MutableList;

/**
 *
 * @author Ollie
 */
public interface GenericMutableWrappedList<T> extends GenericMutableWrappedCollection<T>, MutableList<T> {

    @Override
    java.util.List<T> delegate();

    @Override
    default T set(int index, T element) {
        return this.delegate().set(index, element);
    }

    @Override
    default boolean removeAll(final Object element) {
        return this.delegate().removeAll(Collections.singletonList(element));
    }

}
