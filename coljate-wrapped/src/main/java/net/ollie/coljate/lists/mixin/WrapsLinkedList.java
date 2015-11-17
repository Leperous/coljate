package net.ollie.coljate.lists.mixin;

import javax.annotation.Nonnull;

import net.ollie.coljate.Collection;
import net.ollie.coljate.lists.List;

/**
 * Some {@link List} that wraps a {@link java.util.LinkedList}.
 *
 * @author Ollie
 */
public interface WrapsLinkedList<T> extends WrapsList<T> {

    @Override
    java.util.LinkedList<T> copyDelegate();

    static <T> java.util.LinkedList<T> copyIntoLinkedList(@Nonnull final java.util.Collection<? extends T> collection) {
        return new java.util.LinkedList<>(collection);
    }

    static <T> java.util.LinkedList<T> copyIntoLinkedList(@Nonnull final Collection<? extends T> collection) {
        final java.util.LinkedList<T> list = new java.util.LinkedList<>();
        collection.forEach(list::add);
        return list;
    }

}
