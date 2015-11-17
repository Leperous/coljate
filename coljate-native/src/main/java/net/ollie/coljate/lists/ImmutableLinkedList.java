package net.ollie.coljate.lists;

import net.ollie.coljate.Collection;
import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.utils.DelegatedUnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableLinkedList<T> extends NativeLinkedList<T> implements ImmutableList<T> {

    public static <T> ImmutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new ImmutableLinkedList<>(copyIntoLinkedList(collection));
    }

    public static <T> ImmutableList<T> copyOf(final Collection<? extends T> collection) {
        return new ImmutableLinkedList<>(copyIntoLinkedList(collection));
    }

    ImmutableLinkedList(final java.util.LinkedList<T> delegate) {
        super(delegate);
    }

    @Override
    public ImmutableList<T> with(final T element) {
        final java.util.LinkedList<T> copy = this.copyDelegate();
        copy.add(element);
        return new ImmutableLinkedList<>(copy);
    }

    @Override
    public ImmutableList<T> tail() {
        return copyOf(super.nativeTail());
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return new DelegatedUnmodifiableIterator<>(super.iterator());
    }

}
