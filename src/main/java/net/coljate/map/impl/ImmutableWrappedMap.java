package net.coljate.map.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.Collection;
import net.coljate.collection.ImmutableCollection;
import net.coljate.map.AbstractMap;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.map.ImmutableMap;
import net.coljate.map.Map;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedMap<K, V>
        extends AbstractMap<K, V>
        implements ImmutableMap<K, V> {

    public static <K, V> ImmutableWrappedMap<K, V> createHashMap(final int initialCapacity) {
        return new ImmutableWrappedMap<>(new java.util.HashMap<>(initialCapacity));
    }

    public static <K, V> ImmutableWrappedMap<K, V> copyOf(final Map<? extends K, ? extends V> map) {
        final java.util.Map<K, V> copy = new java.util.HashMap<>(map.count());
        map.forEach(copy::put);
        return new ImmutableWrappedMap<>(copy);
    }

    public static <K, V> ImmutableWrappedMap<K, V> copyOf(final Collection<? extends Entry<? extends K, ? extends V>> entries) {
        final java.util.Map<K, V> map = new java.util.HashMap<>(entries.count());
        entries.forEach(entry -> map.put(entry.key(), entry.value()));
        return new ImmutableWrappedMap<>(map);
    }

    private final java.util.Map<K, V> map;

    protected ImmutableWrappedMap(final java.util.Map<K, V> map) {
        this.map = map;
    }

    @Override
    @SuppressWarnings({"unchecked", "element-type-mismatch"})
    public ImmutableEntry<K, V> getEntry(final Object key) {
        final V value = map.get(key);
        return value == null && !map.containsKey(key)
                ? null
                : ImmutableEntry.of((K) key, value);
    }

    @Override
    public ImmutableSet<K> keys() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableCollection<V> values() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableMap<K, V> with(K key, V value) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public UnmodifiableIterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    protected boolean equals(AbstractMap<?, ?> that) {
        throw new UnsupportedOperationException(); //TODO
    }

}
