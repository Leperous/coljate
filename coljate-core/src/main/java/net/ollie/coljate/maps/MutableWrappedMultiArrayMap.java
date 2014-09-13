package net.ollie.coljate.maps;

import java.util.Iterator;

import net.ollie.coljate.Array;
import net.ollie.coljate.Collection;
import net.ollie.coljate.Map;
import net.ollie.coljate.MultiMap;
import net.ollie.coljate.Set;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.streams.DefaultStream;

/**
 *
 * @author Ollie
 */
public class MutableWrappedMultiArrayMap<K, V>
        implements MultiMap.Mutable<K, V> {

    public static <K, V> MutableWrappedMultiArrayMap<K, V> create() {
        throw new UnsupportedOperationException();
    }

    public static <K, V> MutableWrappedMultiArrayMap<K, V> copy(final Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    public static <K, V> MutableWrappedMultiArrayMap<K, V> copy(final MultiMap<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    protected MutableWrappedMultiArrayMap() {
    }

    @Override
    public Array.Mutable<V> get(Object object) {
        throw new UnsupportedOperationException("get not supported yet!");
    }

    @Override
    public boolean put(K key, V value) {
        throw new UnsupportedOperationException("put not supported yet!");
    }

    @Override
    public Array.Mutable<V> remove(K key) {
        throw new UnsupportedOperationException("remove not supported yet!");
    }

    @Override
    public Set<K> keys() {
        throw new UnsupportedOperationException("keys not supported yet!");
    }

    @Override
    public MutableWrappedMultiArrayMap<K, V> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

    @Override
    public ImmutableWrappedMultiArrayMap<K, V> immutableCopy() {
        throw new UnsupportedOperationException("immutableCopy not supported yet!");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("values not supported yet!");
    }

    @Override
    public Object[] toRawArray() {
        throw new UnsupportedOperationException("toRawArray not supported yet!");
    }

    @Override
    public Streamable<V> tail() {
        throw new UnsupportedOperationException("tail not supported yet!");
    }

    @Override
    public V head() {
        throw new UnsupportedOperationException("head not supported yet!");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("isEmpty not supported yet!");
    }

    @Override
    public boolean contains(Object object) {
        throw new UnsupportedOperationException("contains not supported yet!");
    }

    @Override
    public Iterator<V> iterator() {
        throw new UnsupportedOperationException("iterator not supported yet!");
    }

    @Override
    public Stream<V, Streamable<V>> stream() {
        return DefaultStream.create(this);
    }

    @Override
    public Set<V> unique() {
        throw new UnsupportedOperationException("unique not supported yet!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("clear not supported yet!");
    }

}
