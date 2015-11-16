package net.ollie.coljate.maps;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

/**
 *
 * @author Ollie
 */
public class MutableGuavaMultiSetMap<K, V> implements MultiMap.Mutable<K, V> {

    public static <K, V> MultiMap.Mutable<K, V> map(final Iterable<? extends V> iterable, final Function<? super V, ? extends K> keyFunction) {
        final SetMultimap<K, V> multimap = HashMultimap.create();
        iterable.forEach(entry -> multimap.put(keyFunction.apply(entry), entry));
        return new MutableGuavaMultiSetMap<>(multimap);
    }

    public static <K, V> MultiMap.Mutable<K, V> flatMap(final Iterable<? extends V> iterable, final Function<? super V, ? extends Iterable<? extends K>> keyFunction) {
        final SetMultimap<K, V> multimap = HashMultimap.create();
        iterable.forEach(entry -> keyFunction.apply(entry).forEach(key -> multimap.put(key, entry)));
        return new MutableGuavaMultiSetMap<>(multimap);
    }

    private final SetMultimap<K, V> delegate;

    protected MutableGuavaMultiSetMap(final SetMultimap<K, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Set.Mutable<V> get(final K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean put(final K key, final V value) {
        return delegate.put(key, value);
    }

    @Override
    public Set.Mutable<V> remove(final K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<V, K> inverseCopy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public NonNegativeInteger count(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keys() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Streamable<V> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MultiMap.Mutable<K, V> mutableCopy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MultiMap.Immutable<K, V> immutableCopy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<V, ? extends Streamable<V>> stream() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<V> findAny(Predicate<? super V> predicate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unique<V> unique() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        delegate.clear();
    }

}
