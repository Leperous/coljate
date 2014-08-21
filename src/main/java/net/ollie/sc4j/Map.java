package net.ollie.sc4j;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.ollie.sc4j.access.Finite;
import net.ollie.sc4j.imposed.Cached;
import net.ollie.sc4j.imposed.Mutability;
import net.ollie.sc4j.utils.Functions;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * A cache create uniquely-keyed values.
 *
 * @author Ollie
 * @see java.util.Map
 * @see Cache
 */
public interface Map<K, V>
        extends Cached<K, V> {

    @Override
    Set<K> keys();

    Set<? extends Entry<K, V>> entries();

    @Override
    default Map<K, V> filterKeys(Predicate<? super K> predicate) {
        return this.mapKeys(Functions.satisfying(predicate));
    }

    @Override
    default Map<K, V> filter(final Predicate<? super V> predicate) {
        return this.filterValues(predicate);
    }

    @Override
    default Map<K, V> filterValues(final Predicate<? super V> predicate) {
        return this.mapValues(Functions.satisfying(predicate));
    }

    @Nonnull
    @CheckReturnValue
    <K2, V2> Map<K2, V2> mapEntries(Function<? super K, ? extends K2> keyFunction, Function<? super V, ? extends V2> valueFunction);

    @Override
    default <V2> Map<K, V2> map(final Function<? super V, ? extends V2> function) {
        return this.mapValues(function);
    }

    @Override
    default <K2> Map<K2, V> mapKeys(Function<? super K, ? extends K2> function) {
        return this.mapEntries(function, Function.identity());
    }

    @Override
    default <V2> Map<K, V2> mapValues(Function<? super V, ? extends V2> function) {
        return this.mapEntries(Function.identity(), function);
    }

    @Override
    Map.Mutable<K, V> mutableCopy();

    @Override
    Map.Immutable<K, V> immutableCopy();

    default boolean equals(final Map<?, ?> that) {
        return that != null
                && Objects.equals(this.entries(), that.entries());
    }

    @Override
    default int hash() {
        return this.entries().hashCode();
    }

    interface Entry<K, V> {

        @Nonnull
        K key();

        @CheckForNull
        V value();

        @Nonnull
        @CheckReturnValue
        Map.Mutable.Entry<K, V> mutableCopy();

        @Nonnull
        @CheckReturnValue
        Map.Immutable.Entry<K, V> immutableCopy();

        default boolean equals(final Map.Entry<?, ?> that) {
            return Objects.equals(this.key(), that.key())
                    && Objects.equals(this.value(), that.value());
        }

        default int hash() {
            return Objects.hashCode(this.key())
                    + Objects.hashCode(this.value());
        }

    }

    /**
     * @param <K>
     * @param <V>
     */
    @javax.annotation.concurrent.NotThreadSafe
    interface Mutable<K, V>
            extends Map<K, V>, Cached.Mutable<K, V> {

        @Override
        Set.Mutable<K> keys();

        @Override
        Set.Mutable<? extends Map.Mutable.Entry<K, V>> entries();

        @Nonnull
        Map<K, V> putAll(Map<K, V> map);

        @javax.annotation.concurrent.NotThreadSafe
        interface Entry<K, V>
                extends Map.Entry<K, V>, Mutability.Mutable {

            void setValue(V value);

            @Override
            default void clear() {
                this.setValue(null);
            }

        }

        class Inliner<K, V> {

            private final Map.Mutable<K, V> map;

            protected Inliner(final Map.Mutable<K, V> map) {
                this.map = map;
            }

            public Inliner<K, V> put(final K key, final V value) {
                map.put(key, value);
                return this;
            }

            public Inliner<K, V> putAll(final Map<K, V> values) {
                map.putAll(values);
                return this;
            }

            public Inliner<K, V> remove(final Object value) {
                map.remove(value);
                return this;
            }

            public Map.Mutable<K, V> map() {
                return map;
            }

        }

    }

    /**
     * @param <K>
     * @param <V>
     */
    @javax.annotation.concurrent.Immutable
    interface Immutable<K, V>
            extends Map<K, V>, Cached.Immutable<K, V> {

        @Override
        Set.Immutable<K> keys();

        @Override
        Finite.Immutable<V> values();

        @Override
        Set.Immutable<? extends Map.Immutable.Entry<K, V>> entries();

        @CheckReturnValue
        @Nonnull
        Map.Immutable<K, V> with(K key, V value);

        @CheckReturnValue
        @Nonnull
        Map.Immutable<K, V> without(Object key);

        @Override
        default <K2> Map.Immutable<K2, V> mapKeys(Function<? super K, ? extends K2> function) {
            return this.mapEntries(function, Function.identity());
        }

        @Override
        default <V2> Map.Immutable<K, V2> mapValues(Function<? super V, ? extends V2> function) {
            return this.mapEntries(Function.identity(), function);
        }

        @Override
        default <V2> Map.Immutable<K, V2> map(final Function<? super V, ? extends V2> function) {
            return this.mapValues(function);
        }

        @Override
        <K2, V2> Map.Immutable<K2, V2> mapEntries(Function<? super K, ? extends K2> keyFunction, Function<? super V, ? extends V2> valueFunction);

        @Override
        default Map.Immutable<K, V> filter(final Predicate<? super V> predicate) {
            return this.mapValues(Functions.satisfying(predicate));
        }

        @CheckReturnValue
        @Nonnull
        default Map.Immutable<K, V> replace(K key, V expectedValue, V newValue) {
            return Objects.equals(expectedValue, this.get(key))
                    ? this.without(key).with(key, newValue)
                    : this;
        }

        @Override
        default Map.Immutable<K, V> filterKeys(Predicate<? super K> predicate) {
            return this.mapKeys(Functions.satisfying(predicate));
        }

        @Override
        default Map.Immutable<K, V> filterValues(Predicate<? super V> predicate) {
            return this.mapValues(Functions.satisfying(predicate));
        }

        @Override
        default Map.Immutable<K, V> immutableCopy() {
            return this;
        }

        interface Entry<K, V>
                extends Map.Entry<K, V>, Mutability.Immutable {

            @Override
            default Map.Immutable.Entry<K, V> immutableCopy() {
                return this;
            }

        }

    }

    @javax.annotation.concurrent.ThreadSafe
    interface Concurrent<K, V>
            extends Map.Mutable<K, V> {

        @Override
        V putIfAbsent(K key, V value);

        @Override
        V putIfAbsent(K key, Supplier<? extends V> supplier);

        @Override
        boolean replace(K key, V expectedValue, V newValue);

    }

}
