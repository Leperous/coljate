package net.ollie.coljate.lists;

import javax.annotation.CheckForNull;

import net.ollie.coljate.MutableCollection;

/**
 *
 * @author Ollie
 */
public interface MutableList<T> extends List<T>, MutableCollection<T> {

    @CheckForNull
    T set(int index, T element);

    @Override
    MutableList<T> tail();

}
