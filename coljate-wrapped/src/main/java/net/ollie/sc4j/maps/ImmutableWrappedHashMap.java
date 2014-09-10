package net.ollie.sc4j.maps;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.sc4j.AbstractWrappedStreamable;
import net.ollie.sc4j.Map;
import net.ollie.sc4j.Set;
import net.ollie.sc4j.access.Streamable;
import net.ollie.sc4j.utils.iterators.Iterators;
import net.ollie.sc4j.utils.iterators.UnmodifiableIterator;


/**
 *
 * @author Ollie
 */
public class ImmutableWrappedHashMap<K, V>
        extends AbstractWrappedStreamable<V>
        implements Map.Immutable<K, V> {

    public static <K, V> Map.Immutable<K, V> create() {
        return new ImmutableWrappedHashMap<>(MutableWrappedHashMap.create());
    }

    public static <K, V> Map.Immutable<K, V> copy(final java.util.Map<? extends K, ? extends V> cached) {
        return new ImmutableWrappedHashMap<>(MutableWrappedHashMap.copy(cached));
    }

    public static <K, V> Map.Immutable<K, V> copy(final Map<? extends K, ? extends V> cached) {
        return new ImmutableWrappedHashMap<>(MutableWrappedHashMap.copy(cached));
    }

    public static <K, V> Collector<K, ?, Map.Immutable<K, V>> collectFirst(final Function<? super K, ? extends V> valueFunction) {
        return collectFirst(Function.identity(), valueFunction);
    }

    public static <T, K, V> Collector<T, ?, Map.Immutable<K, V>> collectFirst(
            final Function<? super T, ? extends K> keyFunction,
            final Function<? super T, ? extends V> valueFunction) {
        return new ImmutableHashMapCollector<>((final Map.Mutable<K, V> map, final T element) -> {
            final K key = keyFunction.apply(element);
            final V value = valueFunction.apply(element);
            map.putIfAbsent(key, value);
        });
    }

    private final Map.Mutable<K, V> delegate;

    protected ImmutableWrappedHashMap(final Map.Mutable<K, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Map.Mutable<K, V> underlying() {
        return delegate;
    }

    @Override
    public V get(final Object key) {
        return delegate.get(key);
    }

    @Override
    public Map.Immutable<K, V> with(final K key, final V value) {
        final Map.Mutable<K, V> map = this.underlying().mutableCopy();
        map.put(key, value);
        return copy(map);
    }

    @Override
    public Map.Immutable<K, V> without(final Object key) {
        final Map.Mutable<K, V> map = this.underlying().mutableCopy();
        map.remove(key);
        return copy(map);
    }

    @Override
    public Set.Immutable<K> keys() {
        return delegate.keys().immutableCopy();
    }

    @Override
    public Streamable.Immutable<V> values() {
        return this.underlying().values().immutableCopy();
    }

    @Override
    public Set.Immutable<? extends Map.Immutable.Entry<K, V>> entries() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Map.Mutable<K, V> mutableCopy() {
        return MutableWrappedHashMap.copy(this);
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return Iterators.unmodifiable(super.iterator());
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Map
                && this.equals((Map) object);
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

    protected static class ImmutableHashMapCollector<T, K, V>
            implements Collector<T, Map.Mutable<K, V>, Map.Immutable<K, V>> {

        private final BiConsumer<Map.Mutable<K, V>, T> accumulator;

        ImmutableHashMapCollector(final BiConsumer<Map.Mutable<K, V>, T> accumulator) {
            this.accumulator = accumulator;
        }

        @Override
        public Supplier<Map.Mutable<K, V>> supplier() {
            return MutableWrappedHashMap::create;
        }

        @Override
        public BiConsumer<Map.Mutable<K, V>, T> accumulator() {
            return accumulator;
        }

        @Override
        public BinaryOperator<Map.Mutable<K, V>> combiner() {
            return (Map.Mutable<K, V> left, Map.Mutable<K, V> right) -> {
                left.putAll(right);
                return left;
            };
        }

        @Override
        public Function<Map.Mutable<K, V>, Map.Immutable<K, V>> finisher() {
            return Map::immutableCopy;
        }

        @Override
        public java.util.Set<Characteristics> characteristics() {
            return java.util.Collections.singleton(Characteristics.UNORDERED);
        }

    }

}
