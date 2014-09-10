package net.ollie.coljate.lists;

import java.util.NoSuchElementException;

import net.ollie.coljate.Array;
import net.ollie.coljate.List;
import net.ollie.coljate.Queue;
import net.ollie.coljate.Sequence;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.utils.iterators.Iterables;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/**
 *
 * @author Ollie
 */
@NotThreadSafe
public class RingBuffer<V>
        implements Queue.Mutable<V> {

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Nonnull
    private static final RingBuffer empty = new RingBuffer(AbstactMutableWrappedArray.create());

    @SuppressWarnings("unchecked")
    @Nonnull
    public static <V> RingBuffer<V> empty() {
        return empty;
    }

    @Nonnull
    public static <V> RingBuffer<V> view(final Array.Mutable<V> array) {
        return new RingBuffer<>(array);
    }

    @Nonnull
    public static <V> RingBuffer<V> copy(final Iterable<? extends V> iterable) {
        return view(AbstactMutableWrappedArray.copy(iterable));
    }

    private final Array.Mutable<V> delegate;
    private int readIndex, writeIndex;

    protected RingBuffer(final Array.Mutable<V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean contains(final Object object) {
        return delegate.contains(object);
    }

    @Override
    public Queue.Mutable<V> mutableCopy() {
        return copy(delegate);
    }

    @Override
    public List.Immutable<V> immutableCopy() {
        return delegate.immutableCopy();
    }

    @Override
    public V first() {
        return this.isEmpty()
                ? null
                : delegate.get(this.mod(readIndex));
    }

    @Override
    public Queue<V> tail() {
        switch (this.size()) {
            case 0:
                return this;
            case 1:
                return empty();
            default:
                final Array.Mutable<V> array = AbstactMutableWrappedArray.create(this.size() - 1);
                for (int i = 1; i < this.size(); i++) {
                    final V value = delegate.get(this.mod(readIndex + i));
                    array.set(i - 1, value);
                }
                return view(array);
        }
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Sequence<V> drain() {
        final Sequence<V> copy = copy(delegate);
        this.clear();
        return copy;
    }

    @Override
    public boolean offer(final V value) {
        if (delegate.isEmpty()) {
            return false;
        }
        delegate.set(this.mod(writeIndex), value);
        writeIndex = mod(writeIndex + 1);
        return true;
    }

    @Override
    public V poll() {
        if (this.isEmpty()) {
            return null;
        }
        return this.doRemove();
    }

    @Override
    public V peek() {
        return delegate.isEmpty()
                ? null
                : delegate.get(this.mod(readIndex));
    }

    @Override
    public V element() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return delegate.get(this.mod(readIndex));
    }

    @Override
    public V remove() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.doRemove();
    }

    private V doRemove() {
        final V removed = delegate.remove(this.mod(readIndex));
        readIndex = this.mod(readIndex + 1);
        return removed;
    }

    @Override
    public Object[] toRawArray() {
        return delegate.toRawArray();
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return UnmodifiableIterator.of(delegate.iterator());
    }

    public int size() {
        return delegate.count().intValue();
    }

    private int mod(final int index) {
        return index % this.size();
    }

    @Override
    public Stream<V, ? extends Streamable<V>> stream() {
        return delegate.stream();
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + Iterables.safelyToString(delegate, this);
    }

}
