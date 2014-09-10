package net.ollie.sc4j;

import net.ollie.sc4j.access.Streamable;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.imposed.Distinctness.Duplicated;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface MultiMap<K, V>
        extends Keyed.Multiple<K, V>, Streamable<V>, Duplicated<V> {

    @Override
    @Nonnull
    Streamable<V> get(Object key);

    @Override
    default NonNegativeInteger count(final Object object) {
        return this.get(object).count();
    }

    @Override
    Set<K> keys();

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
        Streamable.Mutable<V> get(Object object);

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
        Streamable.Immutable<V> get(Object object);

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
        default MultiMap.Immutable<K, V> immutableCopy() {
            return this;
        }

    }

}
