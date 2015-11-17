package net.ollie.coljate.lists;

import net.ollie.coljate.lists.mixin.WrappedLinkedList;
import net.ollie.coljate.Collection;
import net.ollie.coljate.UnmodifiableIterator;
import static net.ollie.coljate.lists.mixin.WrappedLinkedList.copyIntoLinkedList;
import net.ollie.coljate.lists.mixin.GenericImmutableWrappedList;
import net.ollie.coljate.utils.DelegatedUnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedLinkedList<T> extends WrappedList<T> implements GenericImmutableWrappedList<T>, WrappedLinkedList<T> {

    public static <T> ImmutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new ImmutableWrappedLinkedList<>(copyIntoLinkedList(collection));
    }

    public static <T> ImmutableList<T> copyOf(final Collection<? extends T> collection) {
        return new ImmutableWrappedLinkedList<>(copyIntoLinkedList(collection));
    }

    private final java.util.LinkedList<T> delegate;

    ImmutableWrappedLinkedList(final java.util.LinkedList<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.LinkedList<T> copyDelegate() {
        return copyIntoLinkedList(delegate);
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
