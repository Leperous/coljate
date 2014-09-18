package net.ollie.coljate.maps;

import java.util.function.Function;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.sets.Set;
import static net.ollie.coljate.utils.Objects.coalesce;

/**
 *
 * @author Ollie
 */
public class MapCache<K, V> implements CacheBuilder<K, V> {

    public static <K, V> MapCache<K, V> create(final Function<? super K, ? extends V> function) {
        return new MapCache<>(MutableWrappedHashMap.create(), function);
    }

    private final Map.Mutable<K, V> map;
    private final Function<? super K, ? extends V> valueFunction;

    protected MapCache(
            final Map.Mutable<K, V> map,
            final Function<? super K, ? extends V> valueFunction) {
        this.map = map;
        this.valueFunction = valueFunction;
    }

    @Override
    public V get(final K key) {
        V got = map.get(key);
        if (got == null && !map.containsKey(key)) {
            got = valueFunction.apply(key);
            got = coalesce(map.putIfAbsent(key, got), got);
            this.onWrite(key, got);
        } else {
            this.onRead(key);
        }
        return got;
    }

    protected void onWrite(K key, V value) {
    }

    protected void onRead(K key) {
    }

    @Override
    public V getIfPresent(final K key) {
        this.onRead(key);
        return map.get(key);
    }

    @Override
    public V put(final K key, final V value) {
        this.onWrite(key, value);
        return map.put(key, value);
    }

    @Override
    public V remove(final Object key) {
        return map.remove(key);
    }

    @Override
    public <V2> Cache<K, V2> recompute(final Function<? super V, ? extends V2> function) {
        throw new UnsupportedOperationException("recompute not supported yet!");
    }

    @Override
    public Set<K> keys() {
        return map.keys();
    }

    @Override
    public Streamable<V> values() {
        return map.values();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Map.Mutable<K, V> mutableCopy() {
        return map.mutableCopy();
    }

    @Override
    public Map.Immutable<K, V> immutableCopy() {
        return map.immutableCopy();
    }

    @Override
    public String toString() {
        return "MapCache";
    }

}
