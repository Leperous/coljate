package net.ollie.coljate.lists;

import java.util.LinkedList;

import net.ollie.coljate.Collection;

/**
 *
 * @author Ollie
 */
public class WrappedLinkedList<T> extends WrappedList<T> {

    public static <T> java.util.LinkedList<T> copyIntoLinkedList(final java.util.Collection<? extends T> collection) {
        return new java.util.LinkedList<>(collection);
    }

    public static <T> java.util.LinkedList<T> copyIntoLinkedList(final Collection<? extends T> collection) {
        final LinkedList<T> list = new java.util.LinkedList<>();
        collection.forEach(list::add);
        return list;
    }

    final java.util.LinkedList<T> delegate;

    protected WrappedLinkedList(final java.util.LinkedList<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    protected java.util.LinkedList<T> copyDelegate() {
        return copyIntoLinkedList(delegate);
    }

}
