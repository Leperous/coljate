package net.ollie.coljate.list;

import net.ollie.coljate.Collection;
import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.list.mixin.WrapsImmutableList;
import net.ollie.coljate.list.mixin.WrapsLinkedList;
import static net.ollie.coljate.list.mixin.WrapsLinkedList.copyIntoLinkedList;
import net.ollie.coljate.utils.DelegatedUnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedLinkedList<T>
        extends WrappedList<T>
        implements WrapsImmutableList<T>, WrapsLinkedList<T> {

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
