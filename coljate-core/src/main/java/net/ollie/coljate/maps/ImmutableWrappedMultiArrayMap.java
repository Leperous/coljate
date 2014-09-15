package net.ollie.coljate.maps;


import net.ollie.coljate.AbstractWrappedStreamable;
import net.ollie.coljate.lists.Array;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedMultiArrayMap<K, V>
        extends AbstractWrappedStreamable<V>
        implements MultiMap.Immutable<K, V> {

    public static <K, V> ImmutableWrappedMultiArrayMap<K, V> create() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    public static <K, V> ImmutableWrappedMultiArrayMap<K, V> copy(final Map<? extends K, ? extends V> map) {
        return new ImmutableWrappedMultiArrayMap<>(MutableWrappedMultiArrayMap.copy(map));
    }

    @SuppressWarnings("unchecked")
    public static <K, V> ImmutableWrappedMultiArrayMap<K, V> copy(final MultiMap<? extends K, ? extends V> map) {
        return map instanceof ImmutableWrappedMultiArrayMap
                ? (ImmutableWrappedMultiArrayMap<K, V>) map
                : new ImmutableWrappedMultiArrayMap<>(MutableWrappedMultiArrayMap.copy(map));
    }

    private final MutableWrappedMultiArrayMap<K, V> underlying;

    protected ImmutableWrappedMultiArrayMap(final MutableWrappedMultiArrayMap<K, V> underlying) {
        this.underlying = underlying;
    }

    @Override
    protected MutableWrappedMultiArrayMap<K, V> underlying() {
        return underlying;
    }

    @Override
    public Array.Immutable<V> get(final Object object) {
        return underlying.get(object).immutableCopy();
    }

    @Override
    public ImmutableWrappedMultiArrayMap<K, V> with(final K key, final V value) {
        throw new UnsupportedOperationException("with not supported yet!");
    }

    @Override
    public ImmutableWrappedMultiArrayMap<K, V> without(final K key) {
        throw new UnsupportedOperationException("without not supported yet!");
    }

    @Override
    public ImmutableWrappedMultiArrayMap<K, V> without(final K key, final V value) {
        throw new UnsupportedOperationException("without not supported yet!");
    }

    @Override
    public Set.Immutable<K> keys() {
        return this.underlying().keys().immutableCopy();
    }

    @Override
    public Array.Immutable<V> values() {
        throw new UnsupportedOperationException("values not supported yet!");
    }

    @Override
    public Set.Immutable<V> unique() {
        return this.underlying().unique().immutableCopy();
    }

    @Override
    public NonNegativeInteger count(final Object object) {
        return this.underlying().count(object);
    }

    @Override
    public Immutable<V> tail() {
        throw new UnsupportedOperationException("tail not supported yet!");
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        throw new UnsupportedOperationException("iterator not supported yet!");
    }

    @Override
    public MutableWrappedMultiArrayMap<K, V> mutableCopy() {
        return MutableWrappedMultiArrayMap.copy(this.underlying());
    }

    @Override
    public ImmutableWrappedMultiArrayMap<K, V> immutableCopy() {
        return this;
    }

}
