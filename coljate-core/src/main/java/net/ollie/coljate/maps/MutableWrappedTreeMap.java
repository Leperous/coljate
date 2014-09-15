package net.ollie.coljate.maps;

import net.ollie.coljate.DefaultStreamable;
import net.ollie.coljate.Map;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.TreeMap;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.imposed.sorting.Sorter;
import net.ollie.coljate.sets.ImmutableWrappedHashSet;
import net.ollie.coljate.sets.MutableWrappedHashSet;

import java.lang.reflect.Field;

import java.util.Comparator;

/**
 * @author Ollie
 */
public class MutableWrappedTreeMap<K, V>
        implements TreeMap.Mutable<K, V> {

    public static <K extends Comparable<? super K>, V> TreeMap.Mutable<K, V> create() {
        return create(null);
    }

    public static <K, V> TreeMap.Mutable<K, V> create(final Comparator<? super K> comparator) {
        return view(new java.util.TreeMap<>(comparator));
    }

    public static <K, V> TreeMap.Mutable<K, V> copy(final java.util.SortedMap<K, ? extends V> map) {
        return view(new java.util.TreeMap<K, V>(map));
    }

    public static <K, V> TreeMap.Mutable<K, V> view(final java.util.TreeMap<K, V> map) {
        return new MutableWrappedTreeMap<>(map);
    }

    private static final Field rootField;

    static {
        try {
            rootField = java.util.TreeMap.class.getField("root");
            rootField.setAccessible(true);
        } catch (final NoSuchFieldException nfex) {
            throw new Error(nfex);
        }
    }

    private final java.util.TreeMap<K, V> delegate;

    protected MutableWrappedTreeMap(final java.util.TreeMap<K, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Sorter<? super K> sorter() {
        return Sorter.create(delegate.comparator());
    }

    @Override
    public K first() {
        return delegate.firstKey();
    }

    @Override
    @SuppressWarnings("unchecked")
    public K root() {
        try {
            return (K) rootField.get(delegate); //TODO non-reflective way?
        } catch (final IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public K parent(final K node) {
        throw new UnsupportedOperationException("parent not supported yet!");
    }

    @Override
    public Set<K> children(final K node) {
        throw new UnsupportedOperationException("children not supported yet!");
    }

    @Override
    public Set<K> neighbours(final K node) {
        return ImmutableWrappedHashSet.copy(this.children(node)).andMaybe(this.parent(node));
    }

    @Override
    public Set<? extends TreeMap<K, V>> subtrees(final K node) {
        throw new UnsupportedOperationException("subtrees not supported yet!");
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public V get(final Object key) {
        return delegate.get(key);
    }

    @Override
    public Map<K, V> putAll(final Map<K, V> map) {
        final Map.Mutable<K, V> put = MutableWrappedHashMap.create();
        for (final Map.Entry<K, V> entry : map.entries()) {
            put.put(entry.key(), this.put(entry.key(), entry.value()));
        }
        return put;
    }

    @Override
    public V put(final K key, final V value) {
        return delegate.put(key, value);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public V remove(final Object key) {
        return delegate.remove(key);
    }

    @Override
    public Streamable<V> values() {
        return DefaultStreamable.view(delegate.values());
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Set.Mutable<K> keys() {
        return MutableWrappedHashSet.view(delegate.keySet());
    }

    @Override
    public Set.Mutable<? extends Map.Mutable.Entry<K, V>> entries() {
        throw new UnsupportedOperationException("entries not supported yet!");
    }

    @Override
    public TreeMap.Mutable<K, V> mutableCopy() {
        return copy(delegate);
    }

    @Override
    public TreeMap.Immutable<K, V> immutableCopy() {
        return ImmutableWrappedTreeMap.copy(delegate);
    }

    @Override
    public String toString(String separator) {
        throw new UnsupportedOperationException("toString not supported yet!");
    }

}
