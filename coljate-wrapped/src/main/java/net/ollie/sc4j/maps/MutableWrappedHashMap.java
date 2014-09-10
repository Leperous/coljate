package net.ollie.sc4j.maps;

import java.util.Iterator;

import net.ollie.sc4j.StreamableWrapper;
import net.ollie.sc4j.Map;
import net.ollie.sc4j.Set;
import net.ollie.sc4j.access.Streamable;
import net.ollie.sc4j.sets.ImmutableWrappedHashSet;
import net.ollie.sc4j.sets.MutableWrappedHashSet;
import net.ollie.sc4j.streams.DefaultStream;
import net.ollie.sc4j.utils.ArrayLists;
import net.ollie.sc4j.utils.Conditions;
import net.ollie.sc4j.utils.iterators.Iterators;
import net.ollie.sc4j.utils.Strings;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public class MutableWrappedHashMap<K, V>
        extends AbstractMutableWrappedMap<K, V> {

    public static final int DEFAULT_INITIAL_CAPACITY = 10;

    @Nonnull
    public static <K, V> Map.Mutable<K, V> create() {
        return create(DEFAULT_INITIAL_CAPACITY);
    }

    @Nonnull
    public static <K, V> Map.Mutable<K, V> create(final NonNegativeInteger initialCapacity) {
        return create(initialCapacity.intValue());
    }

    @Nonnull
    public static <K, V> Map.Mutable<K, V> create(final int initialCapacity) {
        return view(new java.util.HashMap<>(initialCapacity));
    }

    @Nonnull
    public static <K, V> Map.Mutable<K, V> view(@Nonnull final java.util.Map<K, V> map) {
        return new MutableWrappedHashMap<>(map);
    }

    @Nonnull
    public static <K, V> Map.Mutable<K, V> copy(@Nonnull final java.util.Map<? extends K, ? extends V> map) {
        return view(new java.util.HashMap<>(map));
    }

    @Nonnull
    public static <K, V> Map.Mutable<K, V> copy(final Map<? extends K, ? extends V> keyed) {
        final java.util.Map<K, V> map = new java.util.HashMap<>(keyed.count().intValue());
        keyed.entries().forEach(entry -> map.put(entry.key(), entry.value()));
        return view(map);
    }

    private final java.util.Map<K, V> delegate;

    protected MutableWrappedHashMap(final java.util.Map<K, V> underlying) {
        this.delegate = Conditions.checkNotNull(underlying);
    }

    @Override
    protected java.util.Map<K, V> delegate() {
        return delegate;
    }

    @Override
    public Set.Mutable<K> keys() {
        return MutableWrappedHashSet.view(delegate.keySet());
    }

    private transient Set.Mutable<Map.Mutable.Entry<K, V>> entries;

    @Override
    public Set.Mutable<Map.Mutable.Entry<K, V>> entries() {
        if (entries == null) {
            entries = new EntrySet(delegate.entrySet());
        }
        return entries;
    }

    private transient Streamable<V> values;

    @Override
    public Streamable<V> values() {
        if (values == null) {
            values = StreamableWrapper.view(delegate.values());
        }
        return values;
    }

    @Override
    public Map<K, V> putAll(final Map<K, V> map) {
        if (map.isEmpty()) {
            return ImmutableWrappedHashMap.create();
        }
        final Map.Mutable<K, V> out = create(map.count());
        map.entries().forEach(entry -> out.put(entry.key(), this.put(entry.key(), entry.value())));
        return out;
    }

    @Override
    public Map.Mutable<K, V> mutableCopy() {
        return copy(this);
    }

    @Override
    public Map.Immutable<K, V> immutableCopy() {
        return ImmutableWrappedHashMap.copy(this);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Map
                && this.equals((Map<?, ?>) object);
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

    @Override
    public String toString() {
        return this.entries().toString();
    }

    @Override
    public String toString(final String separator) {
        return this.entries().toString(separator);
    }

    protected java.util.Map.Entry<K, V> convert(final Map.Entry<K, V> entry) {
        return new java.util.AbstractMap.SimpleEntry<>(entry.key(), entry.value());
    }

    private final class EntrySet
            implements Set.Mutable<Map.Mutable.Entry<K, V>> {

        private final java.util.Set<java.util.Map.Entry<K, V>> entries;

        EntrySet(final java.util.Set<java.util.Map.Entry<K, V>> entries) {
            this.entries = entries;
        }

        @SuppressWarnings("unchecked")
        Object maybeTransformToNativeEntry(final Object object) {
            if (object instanceof Map.Entry) {
                return MutableWrappedHashMap.this.convert((Map.Entry) object);
            } else {
                return object;
            }
        }

        @Override
        public boolean add(final Map.Mutable.Entry<K, V> value) {
            return entries.add(MutableWrappedHashMap.this.convert(value));
        }

        @Override
        @SuppressWarnings({"element-type-mismatch", "SuspiciousMethodCalls"})
        public boolean remove(final Object value) {
            return entries.remove(this.maybeTransformToNativeEntry(value));
        }

        @Override
        @SuppressWarnings({"element-type-mismatch", "SuspiciousMethodCalls"})
        public boolean contains(final Object object) {
            return entries.contains(this.maybeTransformToNativeEntry(object));
        }

        @Override
        public Set<Entry<K, V>> tail() {
            throw new UnsupportedOperationException("tail not supported yet!");
        }

        @Override
        public Set.Mutable<Map.Mutable.Entry<K, V>> mutableCopy() {
            return MutableWrappedHashSet.copy(this);
        }

        @Override
        public Set.Immutable<Map.Mutable.Entry<K, V>> immutableCopy() {
            return ImmutableWrappedHashSet.copy(this);
        }

        @Override
        public Object[] toRawArray() {
            return ArrayLists.copy(this).toArray();
        }

        @Override
        public boolean isEmpty() {
            return entries.isEmpty();
        }

        @Override
        public Iterator<Map.Mutable.Entry<K, V>> iterator() {
            return Iterators.transform(entries, MutableWrappedHashMap.this::convert);
        }

        @Override
        public Stream<Entry<K, V>, Set<Entry<K, V>>> stream() {
            return DefaultStream.create(this, ImmutableWrappedHashSet::collector);
        }

        @Override
        public void clear() {
            entries.clear();
        }

        @Override
        public boolean equals(final Object object) {
            return object instanceof Set
                    && this.equals((Set) object);
        }

        @Override
        public int hashCode() {
            return this.hash();
        }

        @Override
        public String toString(final String separator) {
            return Strings.toString(this, entries, separator);
        }

    }

}
