package net.ollie.coljate.maps;

import java.util.Objects;

import net.ollie.coljate.ImmutableGuavaCollection;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.sets.ImmutableGuavaSet;
import net.ollie.coljate.sets.ImmutableWrappedHashSet;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import com.google.common.collect.ImmutableSetMultimap;

/**
 *
 * @author Ollie
 */
public class ImmutableGuavaMultiSetMap<K, V> implements MultiMap.Immutable<K, V> {

    public static <K, V> MultiMap.Immutable<K, V> build(final ImmutableSetMultimap.Builder<K, V> builder) {
        return view(builder.build());
    }

    public static <K, V> MultiMap.Immutable<K, V> view(final ImmutableSetMultimap<K, V> map) {
        return new ImmutableGuavaMultiSetMap<>(map);
    }

    private final ImmutableSetMultimap<K, V> delegate;
    private transient Set.Immutable<K> keys;
    private transient Streamable.Immutable<V> values;

    protected ImmutableGuavaMultiSetMap(final ImmutableSetMultimap<K, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Set.Immutable<V> get(final K key) {
        return ImmutableGuavaSet.view(delegate.get(key));
    }

    @Override
    public MultiMap.Immutable<K, V> with(final K key, final V value) {
        final ImmutableSetMultimap.Builder<K, V> builder = ImmutableSetMultimap.builder();
        builder.putAll(delegate);
        builder.put(key, value);
        return build(builder);
    }

    @Override
    public MultiMap.Immutable<K, V> without(final K key) {
        final ImmutableSetMultimap.Builder<K, V> builder = ImmutableSetMultimap.builder();
        delegate.entries().forEach(entry -> {
            if (!Objects.equals(key, entry.getKey())) {
                builder.put(entry);
            }
        });
        return build(builder);
    }

    @Override
    public MultiMap.Immutable<K, V> without(final K key, final V value) {
        final ImmutableSetMultimap.Builder<K, V> builder = ImmutableSetMultimap.builder();
        delegate.entries().forEach(entry -> {
            if (!Objects.equals(key, entry.getKey()) && !Objects.equals(value, entry.getValue())) {
                builder.put(entry);
            }
        });
        return build(builder);
    }

    @Override
    public NonNegativeInteger count(final Object object) {
        return NonNegativeInteger.of(delegate.entries().stream().filter(entry -> Objects.equals(object, entry.getKey())).count());
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
    public Map<V, K> inverseCopy() {
        throw new UnsupportedOperationException("inverse not supported yet!");
    }

    @Override
    public Set<V> unique() {
        return delegate.values().stream().collect(ImmutableWrappedHashSet.collector());
    }

    @Override
    public MultiMap.Mutable<K, V> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

}
