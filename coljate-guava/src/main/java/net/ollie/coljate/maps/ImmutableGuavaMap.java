package net.ollie.coljate.maps;

import java.util.Objects;

import net.ollie.coljate.ImmutableGuavaCollection;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.sets.ImmutableGuavaSet;
import net.ollie.coljate.sets.MutableWrappedHashSet;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class ImmutableGuavaMap<K, V> extends Map.Abstract<K, V> implements Map.Immutable<K, V>, Serializable {

    private static final long serialVersionUID = 1L;

    public static <K, V> ImmutableGuavaMap<K, V> build(final ImmutableMap.Builder<K, V> builder) {
        return view(builder.build());
    }

    @Nonnull
    public static <K, V> ImmutableGuavaMap<K, V> view(final ImmutableMap<K, V> map) {
        return new ImmutableGuavaMap<>(map);
    }

    private final ImmutableMap<K, V> delegate;
    private transient Set.Immutable<K> keys;
    private transient Streamable.Immutable<V> values;
    private transient EntrySet entries;

    protected ImmutableGuavaMap(final ImmutableMap<K, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Set.Immutable<K> keys() {
        return keys == null ? (keys = ImmutableGuavaSet.view(delegate.keySet())) : keys;
    }

    @Override
    public Streamable.Immutable<V> values() {
        return values == null ? (values = ImmutableGuavaCollection.view(delegate.values())) : values;
    }

    @Override
    public Set.Immutable<? extends Map.Immutable.Entry<K, V>> entries() {
        return entries == null ? (entries = new EntrySet()) : entries;
    }

    @Override
    public Immutable<K, V> with(final K key, final V value) {
        final ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        builder.putAll(delegate);
        builder.put(key, value);
        return build(builder);
    }

    @Override
    public Immutable<K, V> without(final Object key) {
        return view(delegate.entrySet()
                .stream()
                .filter(entry -> !Objects.equals(entry.getKey(), key))
                .collect(ImmutableMapCollector.collector()));
    }

    @Override
    public Mutable<K, V> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public V maybeGet(final Object key) {
        return delegate.get(key);
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    private final class EntrySet
            extends Set.Abstract<Map.Immutable.Entry<K, V>>
            implements Set.Immutable<Map.Immutable.Entry<K, V>> {

        @Override
        public Set.Immutable<Map.Immutable.Entry<K, V>> and(final Map.Immutable.Entry<K, V> value) {
            return this.mutableCopy().and(value).immutableCopy();
        }

        @Override
        public Set.Immutable<Map.Immutable.Entry<K, V>> not(final Object value) {
            return this.mutableCopy().not(value).immutableCopy();
        }

        @Override
        public Set.Mutable<Map.Immutable.Entry<K, V>> mutableCopy() {
            return MutableWrappedHashSet.copy(this);
        }

        @Override
        public Stream<Map.Immutable.Entry<K, V>, Set.Immutable<Map.Immutable.Entry<K, V>>> stream() {
            return DefaultStream.create(this, ImmutableGuavaSet::collector);
        }

        @Override
        public UnmodifiableIterator<Map.Immutable.Entry<K, V>> iterator() {
            return UnmodifiableIterator.map(delegate.entrySet(), ImmutableMapEntry::copy);
        }

    }

}
