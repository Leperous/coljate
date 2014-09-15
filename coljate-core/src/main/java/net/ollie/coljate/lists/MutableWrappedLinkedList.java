package net.ollie.coljate.lists;

import net.ollie.coljate.AbstractNativeStreamable;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.Arrays;
import net.ollie.coljate.utils.iterators.Iterables;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * A mutable list implementation that is backed by a native {@link java.util.Deque deque}.
 *
 * @author Ollie
 */
@NotThreadSafe
public class MutableWrappedLinkedList<V>
        extends AbstractNativeStreamable<V>
        implements List.Mutable<V> {

    public static <V> List.Mutable<V> create() {
        return view(new java.util.LinkedList<>());
    }

    @SafeVarargs
    public static <V> List.Mutable<V> create(final V... elements) {
        return MutableWrappedArray.create(elements);
    }

    public static <V> MutableWrappedLinkedList<V> view(final java.util.LinkedList<V> deque) {
        return new MutableWrappedLinkedList<>(deque);
    }

    public static <V> MutableWrappedLinkedList<V> copy(final Iterable<? extends V> iterable) {
        final java.util.LinkedList<V> deque = new java.util.LinkedList<>();
        for (final V item : iterable) {
            deque.add(item);
        }
        return view(deque);
    }

    private final java.util.LinkedList<V> delegate;

    protected MutableWrappedLinkedList(final java.util.LinkedList<V> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected java.util.Deque<V> delegate() {
        return delegate;
    }

    @Override
    public void prefix(final V value) {
        delegate.addFirst(value);
    }

    @Override
    public void prefixAll(final Iterable<? extends V> values) {
        final java.util.List<? extends V> list = Arrays.asList(values);
        final java.util.ListIterator<? extends V> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            delegate.addFirst(iterator.previous());
        }
    }

    @Override
    public void suffix(final V value) {
        delegate.addLast(value);
    }

    @Override
    public void suffixAll(final Iterable<? extends V> values) {
        delegate.addAll(Iterables.toCollection(values));
    }

    @Override
    public List<V> tail() {
        return copy(delegate.subList(1, delegate.size()));
    }

    @Override
    public List.Mutable<V> mutableCopy() {
        return MutableWrappedLinkedList.copy(this);
    }

    @Override
    public List.Immutable<V> immutableCopy() {
        return ImmutableWrappedList.copy(this);
    }

    @Override
    public V first() {
        return delegate.peekFirst();
    }

    @Override
    public V last() {
        return delegate.peekLast();
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Stream<V, ? extends List<V>> stream() {
        return DefaultStream.create(this, ImmutableWrappedList::collector);
    }

    @Override
    public List.Mutable<V> reverse() {
        java.util.Collections.reverse(delegate);
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof List
                && this.equals((List) object);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

}
