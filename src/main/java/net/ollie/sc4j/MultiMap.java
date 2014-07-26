package net.ollie.sc4j;

import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.access.Keyed;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface MultiMap<K, V>
        extends Keyed.Multiple<K, V>, Iteratable<V> {

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
            extends MultiMap<K, V>, Iteratable.Mutable<V> {

        boolean put(K key, V value);

        default boolean putAll(@Nonnull final Map<K, V> map) {
            boolean put = false;
            for (final Map.Entry<K, V> entry : map.entries()) {
                put |= this.put(entry.key(), entry.value());
            }
            return put;
        }

        @Nonnull
        Collection<V> remove(K key);

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
            extends MultiMap<K, V>, Iteratable.Immutable<V> {

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
        Collection.Immutable<V> values();

        @Override
        default MultiMap.Immutable<K, V> immutableCopy() {
            return this;
        }

    }

    interface MultiIterableMap<K, V, C extends Iteratable<V>>
            extends MultiMap<K, V> {

        @Override
        C get(Object object);

        default int count(final Object key) {
            return this.get(key).count();
        }

    }

    interface MultiArrayMap<K, V>
            extends MultiIterableMap<K, V, Array<V>> {

        @Override
        @Nonnull
        Array<V> get(Object object);

    }

    interface MultiSetMap<K, V>
            extends MultiIterableMap<K, V, Set<V>> {

    }

}
