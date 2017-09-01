package net.coljate.list;

import net.coljate.list.impl.WrappedListIterator;

import java.util.Iterator;
import java.util.Objects;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @see java.util.ListIterator
 */
public interface ListIterator<T> extends Iterator<T> {

    boolean hasPrevious();

    T previous();

    static <T> ListIterator<T> wrap(final java.util.ListIterator<? extends T> iterator) {
        return new WrappedListIterator<>(iterator);
    }


}
