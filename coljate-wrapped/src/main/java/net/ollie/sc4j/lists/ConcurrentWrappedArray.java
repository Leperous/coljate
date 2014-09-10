package net.ollie.sc4j.lists;

import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

import net.ollie.sc4j.Array;
import net.ollie.sc4j.SortedSet;
import net.ollie.sc4j.streams.DefaultStream;
import net.ollie.sc4j.utils.iterators.Iterators;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @author Ollie
 */
@ThreadSafe
public class ConcurrentWrappedArray<V>
        extends AbstractWrappedArray<V>
        implements Array.Concurrent<V> {

    public static <V> Array.Mutable<V> create() {
        return create(0);
    }

    public static <V> Array.Mutable<V> create(final int capacity) {
        return new ConcurrentWrappedArray<>(new AtomicReferenceArray<>(capacity));
    }

    public static <V> Array.Mutable<V> copy(final V[] input) {
        return new ConcurrentWrappedArray<>(new AtomicReferenceArray<>(input));
    }

    public static <V> Array.Mutable<V> copy(final AtomicReferenceArray<? extends V> input) {
        final AtomicReferenceArray<V> array = new AtomicReferenceArray<>(input.length());
        for (int i = 0; i < input.length(); i++) {
            array.set(i, input.get(i));
        }
        return new ConcurrentWrappedArray<>(array);
    }

    public static <V> Array.Mutable<V> copy(final Iterable<? extends V> iterable) {
        final Array.Mutable<V> array = create();
        array.suffixAll(iterable);
        return array;
    }

    private final AtomicReference<AtomicReferenceArray<V>> reference;

    protected ConcurrentWrappedArray(final AtomicReferenceArray<V> array) {
        this.reference = new AtomicReference<>(array);
    }

    AtomicReferenceArray<V> array() {
        return reference.get();
    }

    @Override
    public NonNegativeInteger capacity() {
        return NonNegativeInteger.of(this.array().length());
    }

    @Override
    public V get(final int index) throws IndexOutOfBoundsException {
        return this.array().get(index);
    }

    @Override
    public V set(final int index, final V value) throws IndexOutOfBoundsException {
        return this.array().getAndSet(index, value);
    }

    @Override
    public void insert(final int index, final V value) throws IndexOutOfBoundsException {
        AtomicReferenceArray<V> current, inserted;
        do {
            current = this.array();
            inserted = new AtomicReferenceArray<>(current.length() + 1);
            for (int i = 0; i < index; i++) {
                inserted.set(i, current.get(i));
            }
            inserted.set(index, value);
            for (int i = index; i < current.length(); i++) {
                inserted.set(i + 1, current.get(i));
            }
        } while (!reference.compareAndSet(current, inserted));
    }

    @Override
    public V first() {
        final AtomicReferenceArray<V> array = reference.get();
        return array.length() == 0
                ? null
                : array.get(0);
    }

    @Override
    public void suffix(final V value) {
        this.suffixAll(ImmutableWrappedArray.create(value));
    }

    @Override
    public Object[] toRawArray() {
        final AtomicReferenceArray<V> input = this.array();
        final Object[] output = new Object[input.length()];
        for (int i = 0; i < input.length(); i++) {
            output[i] = input.get(i);
        }
        return output;
    }

    @Override
    public V last() {
        final AtomicReferenceArray<V> array = this.array();
        return array.length() == 0
                ? null
                : array.get(array.length() - 1);
    }

    @Override
    public Array<V> segment(final int from, final int to) {
        throw new UnsupportedOperationException("segment not supported yet!");
    }

    @Override
    public Array<V> tail() {
        AtomicReferenceArray<V> current, tail;
        do {
            current = this.array();
            final int currentLength = current.length();
            if (currentLength <= 1) {
                return create();
            }
            tail = new AtomicReferenceArray<>(currentLength - 1);
            for (int i = 1; i < currentLength; i++) {
                tail.set(i - 1, current.get(i));
            }
        } while (!reference.compareAndSet(current, current));
        return copy(tail);
    }

    @Override
    public SortedSet<NonNegativeInteger> keys() {
        throw new UnsupportedOperationException("keys not supported yet!");
    }

    @Override
    public void setCapacity(final NonNegativeInteger size) {
        final int s = size.intValue();
        AtomicReferenceArray<V> current, resized;
        do {
            current = this.array();
            resized = new AtomicReferenceArray<>(s); //Need to make a fresh array
            for (int i = 0; i < Math.min(current.length(), s); i++) {
                resized.set(i, current.get(i));
            }
        } while (!reference.compareAndSet(current, resized));
    }

    @Override
    public void prefixAll(final Iterable<? extends V> values) {
        final Array<V> array = ImmutableWrappedArray.copy(values);
        final int c = array.count().intValue();
        AtomicReferenceArray<V> current, prefixed;
        do {
            current = this.array();
            final int currentLength = current.length();
            prefixed = new AtomicReferenceArray<>(currentLength + c);
            //Copy prefix
            for (int i = 0; i < c; i++) {
                prefixed.set(i, array.get(i));
            }
            //Copy current
            for (int i = 0; i < currentLength; i++) {
                prefixed.set(c + i, current.get(i));
            }
        } while (!reference.compareAndSet(current, prefixed));
    }

    @Override
    public void suffixAll(final Iterable<? extends V> values) {
        final Array<V> suffix = ImmutableWrappedArray.copy(values);
        final int c = suffix.count().intValue();
        AtomicReferenceArray<V> current, suffixed;
        do {
            current = this.array();
            final int currentLength = current.length();
            suffixed = new AtomicReferenceArray<>(currentLength + c);
            //Copy current
            for (int i = 0; i < currentLength; i++) {
                suffixed.set(i, current.get(i));
            }
            //Copy suffix
            for (int i = currentLength; i < currentLength + c; i++) {
                suffixed.set(i, suffix.get(i - currentLength));
            }
        } while (!reference.compareAndSet(current, suffixed));
    }

    @Override
    public void sort(final Comparator<? super V> comparator) {
        AtomicReferenceArray<V> current, sorted;
        do {
            current = this.array();
            final int length = current.length();
            sorted = new AtomicReferenceArray<>(length);
            final Array.Mutable<V> array = AbstactMutableWrappedArray.create(length);
            for (int i = 0; i < length; i++) {
                array.suffix(current.get(i));
            }
            array.sort(comparator);
            for (int i = 0; i < length; i++) {
                sorted.set(i, array.get(i));
            }
        } while (!reference.compareAndSet(current, sorted));
    }

    @Override
    public void clear() {
        AtomicReferenceArray<V> current, cleared;
        do {
            current = this.array();
            cleared = new AtomicReferenceArray<>(current.length());
        } while (!reference.compareAndSet(current, cleared));
    }

    @Override
    public Iterator<V> iterator() {
        return Iterators.ofArray(this.array()); //TODO behaviour is ill-defined
    }

    @Override
    public Stream<V, ? extends Array<V>> stream() {
        return DefaultStream.create(this, ImmutableWrappedArray::collector);
    }

    @Override
    public Array.Mutable<V> reverse() {
        throw new UnsupportedOperationException("reverse not supported yet!");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + this.array();
    }

}
