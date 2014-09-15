package net.ollie.coljate.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collector;

import net.ollie.coljate.AbstractNativeTraversable;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

/**
 * A finite queue implementation.
 *
 * @author Ollie
 */
public class MutableWrappedLinkedQueue<V>
        extends AbstractNativeTraversable<V>
        implements Queue.Mutable<V>, Streamable.Mutable<V> {

    public static <V> MutableWrappedLinkedQueue<V> empty() {
        return view(new java.util.LinkedList<>());
    }

    public static <V> MutableWrappedLinkedQueue<V> view(final java.util.Queue<V> queue) {
        return new MutableWrappedLinkedQueue<>(queue);
    }

    public static <V> MutableWrappedLinkedQueue<V> copy(final java.util.Queue<? extends V> queue) {
        return view(new java.util.LinkedList<>(queue));
    }

    public static <V> MutableWrappedLinkedQueue<V> copy(final Iterable<? extends V> iterable) {
        return copy(iterable.iterator());
    }

    public static <V> MutableWrappedLinkedQueue<V> copy(final Iterator<? extends V> iterator) {
        final java.util.Queue<V> queue = new java.util.LinkedList<>();
        while (iterator.hasNext()) {
            queue.add(iterator.next());
        }
        return view(queue);
    }

    public static <V> Collector<V, ?, Queue.Mutable<V>> collector() {
        throw new UnsupportedOperationException(); //TODO queue collector
    }

    private final java.util.Queue<V> queue;

    protected MutableWrappedLinkedQueue(final java.util.Queue<V> queue) {
        this.queue = queue;
    }

    @Override
    protected java.util.Queue<V> delegate() {
        return queue;
    }

    @Override
    public MutableWrappedLinkedQueue<V> mutableCopy() {
        return copy(queue);
    }

    @Override
    public boolean offer(final V value) {
        return queue.offer(value);
    }

    @Override
    public V poll() {
        return queue.poll();
    }

    @Override
    public V peek() {
        return queue.peek();
    }

    @Override
    public V element() throws NoSuchElementException {
        return queue.element();
    }

    @Override
    public V remove() throws NoSuchElementException {
        return queue.remove();
    }

    @Override
    public MutableWrappedLinkedQueue<V> tail() {
        final Iterator<V> iterator = this.iterator();
        if (iterator.hasNext()) {
            iterator.next();
            return copy(iterator);
        } else {
            return this;
        }
    }

    @Override
    public Stream<V, ? extends Streamable<V>> stream() {
        return DefaultStream.create(queue, MutableWrappedLinkedQueue::collector);
    }

    @Override
    public Iterator<V> iterator() {
        return queue.iterator();
    }

    @Override
    public Sequence<V> drain() {
        final List.Mutable<V> sequence = MutableWrappedLinkedList.create();
        final Iterator<V> iterator = this.iterator();
        while (iterator.hasNext()) {
            sequence.suffix(iterator.next());
            iterator.remove();
        }
        return sequence;
    }

    @Override
    public List.Immutable<V> immutableCopy() {
        return ImmutableWrappedList.copy(this);
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public Object[] toRawArray() {
        return queue.toArray();
    }

    @Override
    public NonNegativeInteger count() {
        return NonNegativeInteger.of(queue.size());
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Queue
                && this.equals((Queue<?>) object);
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

}
