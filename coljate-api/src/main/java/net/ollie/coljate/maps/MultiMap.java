package net.ollie.coljate.maps;

import net.ollie.coljate.Collection;
import net.ollie.coljate.access.Keyed;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.imposed.Distinctness.Duplicated;
import net.ollie.coljate.imposed.Invertible.Surjective;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * An invertible one-to-many mapping.
 *
 * @author Ollie
 * @see InvertedMap
 * @see BijectiveMap
 */
public interface MultiMap<K, V>
        extends Keyed.Multiple<K, V>, Streamable<V>, Duplicated<V>, Surjective<K, V> {

    @Override
    @Nonnull
    Streamable<V> get(K key);

    @Override
    Map<V, K> inverseCopy();

    @Override
    NonNegativeInteger count(Object object);

    @Override
    Set<K> keys();

    @Override
    Streamable<V> values();

    @Override
    default Streamable<V> tail() {
        return this.values().tail();
    }

    @Override
    default boolean isEmpty() {
        return Keyed.Multiple.super.isEmpty();
    }

    @Override
    MultiMap.Mutable<K, V> mutableCopy();

    @Override
    MultiMap.Immutable<K, V> immutableCopy();

    interface Mutable<K, V>
            extends MultiMap<K, V>, Streamable.Mutable<V> {

        @Override
        Streamable.Mutable<V> get(K key);

        boolean put(K key, V value);

        default boolean putAll(@Nonnull final Map<K, V> map) {
            boolean put = false;
            for (final Map.Entry<K, V> entry : map.entries()) {
                put |= this.put(entry.key(), entry.value());
            }
            return put;
        }

        @Nonnull
        Streamable.Mutable<V> remove(K key);

        default boolean removeAll(@Nonnull final Iterable<K> iterable) {
            boolean removed = false;
            for (final K key : iterable) {
                final Collection<V> values = this.remove(key);
                removed |= !values.isEmpty();
            }
            return removed;
        }

    }

    interface Immutable<K, V>
            extends MultiMap<K, V>, Streamable.Immutable<V> {

        @Override
        Streamable.Immutable<V> get(K key);

        @Nonnull
        @CheckReturnValue
        MultiMap.Immutable<K, V> with(K key, V value);

        @Nonnull
        @CheckReturnValue
        default MultiMap.Immutable<K, V> withAll(@Nonnull final Map<? extends K, ? extends V> map) {
            MultiMap.Immutable<K, V> with = this;
            for (Map.Entry<? extends K, ? extends V> entry : map.entries()) {
                with = with.with(entry.key(), entry.value());
            }
            return with;
        }

        @Nonnull
        @CheckReturnValue
        MultiMap.Immutable<K, V> without(K key);

        @Nonnull
        @CheckReturnValue
        MultiMap.Immutable<K, V> without(K key, V value);

        @Nonnull
        @CheckReturnValue
        default MultiMap.Immutable<K, V> withoutAll(@Nonnull final Map<? extends K, ? extends V> map) {
            MultiMap.Immutable<K, V> without = this;
            for (Map.Entry<? extends K, ? extends V> entry : map.entries()) {
                without = without.without(entry.key(), entry.value());
            }
            return without;
        }

        @Override
        Set.Immutable<K> keys();

        @Override
        Streamable.Immutable<V> values();

        @Override
        default Stream<V, ? extends Streamable<V>> stream() {
            return this.values().stream();
        }

        @Override
        default Streamable.Immutable<V> tail() {
            return this.values().tail();
        }

        @Override
        default MultiMap.Immutable<K, V> immutableCopy() {
            return this;
        }

    }

}
