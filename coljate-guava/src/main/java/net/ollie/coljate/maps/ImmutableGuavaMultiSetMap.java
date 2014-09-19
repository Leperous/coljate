package net.ollie.coljate.maps;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.sets.Set;

/**
 *
 * @author Ollie
 */
public class ImmutableGuavaMultiSetMap<K, V> implements MultiMap.Immutable<K, V> {

    @Override
    public Set.Immutable<V> get(Object object) {
        throw new UnsupportedOperationException("get not supported yet!");
    }

    @Override
    public MultiMap.Immutable<K, V> with(K key, V value) {
        throw new UnsupportedOperationException("with not supported yet!");
    }

    @Override
    public MultiMap.Immutable<K, V> without(K key) {
        throw new UnsupportedOperationException("without not supported yet!");
    }

    @Override
    public MultiMap.Immutable<K, V> without(K key, V value) {
        throw new UnsupportedOperationException("without not supported yet!");
    }

    @Override
    public Set.Immutable<K> keys() {
        throw new UnsupportedOperationException("keys not supported yet!");
    }

    @Override
    public Streamable.Immutable<V> values() {
        throw new UnsupportedOperationException("values not supported yet!");
    }

    @Override
    public Map<V, K> inverse() {
        throw new UnsupportedOperationException("inverse not supported yet!");
    }

    @Override
    public MultiMap.Mutable<K, V> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

    @Override
    public Stream<V, ? extends Streamable<V>> stream() {
        throw new UnsupportedOperationException("stream not supported yet!");
    }

    @Override
    public Unique<V> unique() {
        throw new UnsupportedOperationException("unique not supported yet!");
    }

}
