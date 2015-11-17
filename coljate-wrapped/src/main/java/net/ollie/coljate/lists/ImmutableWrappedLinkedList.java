package net.ollie.coljate.lists;

import net.ollie.coljate.Collection;
import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.utils.DelegatedUnmodifiableIterator;
import net.ollie.coljate.lists.mixin.GenericWrappedImmutableList;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedLinkedList<T> extends WrappedLinkedList<T> implements GenericWrappedImmutableList<T> {

    public static <T> ImmutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new ImmutableWrappedLinkedList<>(copyIntoLinkedList(collection));
    }

    public static <T> ImmutableList<T> copyOf(final Collection<? extends T> collection) {
        return new ImmutableWrappedLinkedList<>(copyIntoLinkedList(collection));
    }

    ImmutableWrappedLinkedList(final java.util.LinkedList<T> delegate) {
        super(delegate);
    }

    @Override
    public ImmutableList<T> tail() {
        return copyOf(super.nativeTail());
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return new DelegatedUnmodifiableIterator<>(super.iterator());
    }

    @Override
    @Deprecated
    public ImmutableWrappedLinkedList<T> immutableCopy() {
        return this;
    }

}
