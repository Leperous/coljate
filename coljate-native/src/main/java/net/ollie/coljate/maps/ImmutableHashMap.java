package net.ollie.coljate.maps;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.lists.MutableWrappedLinkedList;
import net.ollie.coljate.sets.ImmutableWrappedHashSet;
import net.ollie.coljate.sets.MutableWrappedHashSet;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.iterators.Iterables;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import java.io.Serializable;
import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @see java.util.HashMap
 */
public class ImmutableHashMap<K, V>
        implements Map.Immutable<K, V>, Serializable {

    private static final long serialVersionUID = 1L;
    private static final float LOAD_FACTOR = 0.75f;

    public static <K, V> ImmutableHashMap<K, V> create() {
        return new ImmutableHashMap<>();
    }

    public static <K, V> ImmutableHashMap<K, V> copy(final Map<? extends K, ? extends V> map) {
        if (map instanceof ImmutableHashMap) {
            return (ImmutableHashMap<K, V>) map;
        }
        throw new UnsupportedOperationException(); //TODO
    }

    private final ImmutableMapNode<K, V>[] bucket;

    private transient Set.Immutable<K> keys;
    private transient Streamable.Immutable<V> values;
    private transient Set.Immutable<Map.Immutable.Entry<K, V>> entries;

    @SuppressWarnings({"rawtypes", "unchecked"})
    ImmutableHashMap() {
        this.bucket = new ImmutableMapNode[0];
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    ImmutableHashMap(final K key, final V value) {
        this.bucket = new ImmutableMapNode[]{new ImmutableMapNode<>(key, value, null)};
    }

    private ImmutableHashMap(final ImmutableMapNode<K, V>[] bucket) {
        this.bucket = bucket;
    }

    @Override
    public V maybeGet(final Object key) {
        if (key == null) {
            return null;
        }
        for (final ImmutableMapNode<K, V> node : bucket) {
            ImmutableMapNode<K, V> head = node;
            while (head != null) {
                if (Objects.equals(head.key(), key)) {
                    return node.value();
                }
                head = head.next();
            }
        }
        return null;
    }

    @Override
    public Set.Immutable<K> keys() {
        return keys == null ? (keys = new KeySet()) : keys;
    }

    @Override
    public Streamable.Immutable<V> values() {
        return values == null ? (values = new Values()) : values;
    }

    @Override
    public Set.Immutable<? extends Map.Immutable.Entry<K, V>> entries() {
        return entries == null ? (entries = new EntrySet()) : entries;
    }

    private static int bucketSize(final int numElements) {
        return Math.round(numElements * LOAD_FACTOR);
    }

    private ImmutableMapNode<K, V>[] cloneArray(final int newSize) {
        @SuppressWarnings({"rawtypes", "unchecked"})
        final ImmutableMapNode<K, V>[] cloned = new ImmutableMapNode[newSize];
        //System.arraycopy(bucket, 0, cloned, 0, bucket.length);
        for (final ImmutableMapNode<K, V> node : bucket) {
            ImmutableMapNode<K, V> innerNode = node;
            while (innerNode != null) {
                final int index = bucketFor(innerNode.key(), cloned);
                cloned[index] = new ImmutableMapNode<>(innerNode.key, innerNode.value, cloned[index]);
                innerNode = innerNode.next();
            }
        }
        return cloned;
    }

    @Override
    public ImmutableHashMap<K, V> with(final K key, final V value) {
        final ImmutableMapNode<K, V>[] bucket = this.cloneArray(bucketSize(this.bucket.length + 1)); //FIXME don't always increment!
        final int i = bucketFor(key, bucket);
        ImmutableMapNode<K, V> node = bucket[i];
        node = node == null ? new ImmutableMapNode<>(key, value, null) : node.swap(key, value);
        bucket[i] = node;
        return new ImmutableHashMap<>(bucket);
    }

    @Override
    public ImmutableHashMap<K, V> without(final Object key) {
        final ImmutableMapNode<K, V>[] bucket = this.cloneArray(bucketSize(this.bucket.length + 1)); //FIXME don't always increment!
        final int i = bucketFor(key, bucket);
        ImmutableMapNode<K, V> node = bucket[i];
        if (node == null) {
            return this;
        }
        node = node.delete(key);
        bucket[i] = node;
        return new ImmutableHashMap<>(bucket);
    }

    private static int bucketFor(final Object key, Object[] bucket) {
        return key.hashCode() % bucket.length;
    }

    @Override
    public Map.Mutable<K, V> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

    @Override
    public NonNegativeInteger count() {
        return Arrays.stream(bucket).reduce(NonNegativeInteger.ZERO, (current, next) -> current.plus(next.count()), NonNegativeInteger::plus);
    }

    @Override
    public boolean isEmpty() {
        for (final ImmutableMapNode<K, V> node : bucket) {
            if (node != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public MultiMap.Immutable<V, K> inverseCopy() {
        throw new UnsupportedOperationException("inverse not supported yet!");
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

    @Override
    public boolean equals(final Object that) {
        return that instanceof Map && this.equals((Map) that);
    }

    @Override
    public String toString() {
        return this.entries().toString();
    }

    private static final class ImmutableMapNode<K, V>
            implements Map.Immutable.Entry<K, V>, Serializable {

        private static final long serialVersionUID = 1L;

        private final K key;
        private final V value;
        private final ImmutableMapNode<K, V> next;

        ImmutableMapNode(final K key, final V value, final ImmutableMapNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }

        @CheckForNull
        ImmutableMapNode<K, V> next() {
            return next;
        }

        NonNegativeInteger count() {
            return next == null ? NonNegativeInteger.ONE : NonNegativeInteger.ONE.plus(next.count());
        }

        boolean containsKey(final Object object) {
            return Objects.equals(key, object) || (next != null && next.containsKey(object));
        }

        boolean containsValue(final Object object) {
            return Objects.equals(value, object) || (next != null && next.containsValue(object));
        }

        @Nonnull
        ImmutableMapNode<K, V> swap(final K key, final V value) {
            if (Objects.equals(this.key(), key)) {
                return new ImmutableMapNode<>(key, value, next);
            }
            final ImmutableMapNode<K, V> next = this.next == null ? new ImmutableMapNode<>(key, value, null) : this.next.swap(key, value);
            return new ImmutableMapNode<>(this.key(), this.value(), next);
        }

        @CheckForNull
        ImmutableMapNode<K, V> delete(final Object key) {
            return Objects.equals(this.key, key)
                    ? next
                    : new ImmutableMapNode<>(this.key, value, next == null ? null : next.delete(key));
        }

        @Override
        public Map.Mutable.Entry<K, V> mutableCopy() {
            return MapEntry.mutable(key, value);
        }

        @Override
        public Map.Entry<V, K> inverseCopy() {
            return MapEntry.immutable(value, key);
        }

        @Override
        public boolean equals(final Object object) {
            return object instanceof Map.Entry && this.equals((Map.Entry) object);
        }

        @Override
        public int hashCode() {
            return this.hash();
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }

    }

    private final class KeySet
            extends Set.Abstract<K>
            implements Set.Immutable<K> {

        @Override
        public Stream<K, ? extends Set<K>> stream() {
            return DefaultStream.create(this, ImmutableWrappedHashSet::collector);
        }

        @Override
        public Set.Mutable<K> mutableCopy() {
            return MutableWrappedHashSet.copy(this);
        }

        @Override
        public boolean contains(final Object object) {
            if (this.isEmpty()) {
                return false;
            }
            final int index = bucketFor(object, bucket);
            ImmutableMapNode<K, V> node = bucket[index];
            return node != null && node.containsKey(object);
        }

        @Override
        public UnmodifiableIterator<K> iterator() {
            return new UnmodifiableIterator<K>() {

                final UnmodifiableIterator<? extends Entry<K, V>> iterator = ImmutableHashMap.this.entries().iterator();

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public K next() {
                    return iterator.next().key();
                }

            };
        }

        @Override
        public Set.Immutable<K> and(final K value) {
            return this.mutableCopy().and(value).immutableCopy();
        }

        @Override
        public Set.Immutable<K> not(final Object element) {
            return this.mutableCopy().not(element).immutableCopy();
        }

        @Override
        public Set.Immutable<K> tail() {
            return this.mutableCopy().tail().immutableCopy();
        }

        @Override
        public boolean isEmpty() {
            return ImmutableHashMap.this.isEmpty();
        }

        @Override
        public NonNegativeInteger count() {
            return ImmutableHashMap.this.count();
        }

    }

    private final class Values
            implements Streamable.Immutable<V> {

        @Override
        public Streamable.Mutable<V> mutableCopy() {
            return MutableWrappedLinkedList.copy(this);
        }

        @Override
        public Streamable.Immutable<V> tail() {
            return this.mutableCopy().tail().immutableCopy();
        }

        @Override
        public boolean isEmpty() {
            return ImmutableHashMap.this.isEmpty();
        }

        @Override
        public Stream<V, ? extends Streamable<V>> stream() {
            return DefaultStream.create(this);
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return new UnmodifiableIterator<V>() {

                final UnmodifiableIterator<? extends Map.Immutable.Entry<K, V>> iterator = ImmutableHashMap.this.entries().iterator();

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public V next() {
                    return iterator.next().value();
                }

            };
        }

    }

    private final class EntrySet
            implements Set.Immutable<Map.Immutable.Entry<K, V>> {

        @Override
        public Set.Mutable<Entry<K, V>> mutableCopy() {
            return MutableWrappedHashSet.copy(this);
        }

        @Override
        public Set.Immutable<Entry<K, V>> and(final Map.Immutable.Entry<K, V> value) {
            return this.mutableCopy().and(value).immutableCopy();
        }

        @Override
        public Set.Immutable<Entry<K, V>> not(final Object element) {
            return this.mutableCopy().not(element).immutableCopy();
        }

        @Override
        public Set.Immutable<Entry<K, V>> tail() {
            return this.mutableCopy().tail().immutableCopy();
        }

        @Override
        public boolean isEmpty() {
            return ImmutableHashMap.this.isEmpty();
        }

        @Override
        public UnmodifiableIterator<Entry<K, V>> iterator() {
            return new UnmodifiableIterator<Entry<K, V>>() {

                int index = 0;
                ImmutableMapNode<K, V> nextNode;

                @Override
                public boolean hasNext() {
                    while (nextNode == null) {
                        if (index >= bucket.length) {
                            return false;
                        }
                        nextNode = bucket[index++];
                    }
                    return true;
                }

                @Override
                public Entry<K, V> next() {
                    if (nextNode == null) {
                        throw new NoSuchElementException();
                    }
                    final ImmutableMapNode<K, V> current = nextNode;
                    nextNode = nextNode.next();
                    return current;
                }

            };
        }

        @Override
        public Stream<Entry<K, V>, ? extends Set<Entry<K, V>>> stream() {
            return DefaultStream.create(this, ImmutableWrappedHashSet::collector);
        }

        @Override
        public NonNegativeInteger count() {
            return ImmutableHashMap.this.count();
        }

        @Override
        public String toString() {
            return Iterables.safelyToString(this, this);
        }

        @Override
        public boolean equals(final Object that) {
            return that instanceof Set && this.equals((Set) that);
        }

        @Override
        public int hashCode() {
            return this.hash();
        }

    }

}
