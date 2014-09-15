package net.ollie.coljate.sets;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.lists.ImmutableWrappedList;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.Arrays;
import net.ollie.coljate.utils.Conditions;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
@SuppressWarnings("element-type-mismatch")
public class MutableWrappedMultiHashSet<V>
        implements MultiSet.Mutable<V> {

    public static final int DEFAULT_CAPACITY = 10;

    public static <V> MultiSet.Mutable<V> create() {
        return create(DEFAULT_CAPACITY);
    }

    public static <V> MultiSet.Mutable<V> create(final int initialCapacity) {
        return view(new java.util.HashMap<>(initialCapacity));
    }

    /**
     *
     * @param <V> value type
     * @param map non-null map
     * @return a mutable multiset backed by the given map.
     * @throws IllegalArgumentException if any negative values are present.
     */
    public static <V> MultiSet.Mutable<V> view(@Nonnull final java.util.Map<V, NonNegativeInteger> map) {
        return new MutableWrappedMultiHashSet<>(map);
    }

    @SuppressWarnings("unchecked")
    public static <V> MultiSet.Mutable<V> copy(final V... array) {
        return copy(Arrays.asList(array));
    }

    public static <V> MultiSet.Mutable<V> copy(final Iterable<? extends V> iterable) {
        final java.util.Map<V, NonNegativeInteger> map = new java.util.HashMap<>();
        for (final V element : iterable) {
            final NonNegativeInteger current = map.get(element);
            map.put(element, current == null ? NonNegativeInteger.of(1) : current.increment());
        }
        return view(map);
    }

    private final java.util.Map<V, NonNegativeInteger> delegate;

    protected MutableWrappedMultiHashSet(@Nonnull final java.util.Map<V, NonNegativeInteger> delegate) {
        this.delegate = Conditions.checkNotNull(delegate);
    }

    @Override
    public Set<V> unique() {
        return MutableWrappedHashSet.copy(delegate.keySet());
    }

    @Override
    public NonNegativeInteger count(final Object value) {
        return delegate.getOrDefault(value, NonNegativeInteger.ZERO);
    }

    @Override
    public int increment(final V value) {
        final NonNegativeInteger incremented = this.count(value).increment();
        delegate.put(value, incremented);
        return incremented.intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int decrement(final Object value) {
        final Optional<NonNegativeInteger> decrement = this.count(value).decrement();
        if (decrement.isPresent()) {
            delegate.put((V) value, decrement.get());
            return decrement.get().intValue();
        } else {
            delegate.remove(value);
            return 0;
        }
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean contains(final Object object) {
        return delegate.containsKey(object);
    }

    @Override
    public Iterator<V> iterator() {
        return new RepeatedMutableIterator();
    }

    @Override
    public Stream<V, ? extends Set<V>> stream() {
        return DefaultStream.create(this, MutableWrappedHashSet::collector);
    }

    @Override
    public boolean remove(final Object value) {
        return delegate.remove(value) != null;
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Set<V> keys() {
        return MutableWrappedHashSet.copy(delegate.keySet());
    }

    @Override
    public Streamable<NonNegativeInteger> values() {
        return ImmutableWrappedList.copy(delegate.values());
    }

    @Override
    public Set<V> tail() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object[] toRawArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public V head() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MultiSet.Mutable<V> mutableCopy() {
        return copy(this);
    }

    @Override
    public MultiSet.Immutable<V> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof MultiSet
                && this.equals((MultiSet) object);
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

    @Override
    public String toString(final String separator) {
        return delegate.toString(); //TODO
    }

    //TODO make public
    private final class RepeatedMutableIterator
            implements Iterator<V> {

        final Iterator<java.util.Map.Entry<V, NonNegativeInteger>> iterator = delegate.entrySet().iterator();
        V current;
        NonNegativeInteger count;

        boolean hasCurrent() {
            return current != null && count != null && !count.isZero();
        }

        @Override
        public boolean hasNext() {
            if (!this.hasCurrent() && iterator.hasNext()) {
                final java.util.Map.Entry<V, NonNegativeInteger> next = iterator.next();
                current = next.getKey();
                count = next.getValue();
                return this.hasNext();
            }
            return this.hasCurrent();
        }

        @Override
        public V next() {
            if (!this.hasCurrent()) {
                throw new NoSuchElementException();
            }
            count = count.decrement().get();
            final V next;
            if (count.isZero()) {
                next = current;
                current = null;
            } else {
                next = current;
            }
            return next;
        }

    }

}
