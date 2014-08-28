package net.ollie.sc4j;

import java.util.Objects;
import java.util.function.Function;

import net.ollie.sc4j.access.Finite;
import net.ollie.sc4j.imposed.Cached;
import net.ollie.sc4j.imposed.Mapping.Surjective;
import net.ollie.sc4j.imposed.Mutability;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * A cache create uniquely-keyed values.
 *
 * The mapping is potentially {@link Surjective} (in that it may contain copies of the same value) but not
 * {@link Injective} (in that null keys are not allowed).
 *
 * @author Ollie
 * @see java.util.Map
 * @see Cache
 */
public interface Map<K, V>
        extends Cached<K, V>, Surjective<K, V> {

    @Override
    Set<K> keys();

    Set<? extends Entry<K, V>> entries();

    @Nonnull
    @CheckReturnValue
    <K2, V2> Map<K2, V2> mapEntries(Function<? super K, ? extends K2> keyFunction, Function<? super V, ? extends V2> valueFunction);

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

        @Nonnull
        default Inliner<K, V, ? extends Map.Mutable<K, V>> inline() {
            return new Inliner<>(this);
        }

        @javax.annotation.concurrent.NotThreadSafe
        interface Entry<K, V>
                extends Map.Entry<K, V>, Mutability.Mutable {

            void setValue(V value);

            @Override
            default void clear() {
                this.setValue(null);
            }

        }

        class Inliner<K, V, M extends Map.Mutable<K, V>> {

            private final M map;

            protected Inliner(final M map) {
                this.map = map;
            }

            public Inliner<K, V, M> put(final K key, final V value) {
                map.put(key, value);
                return this;
            }

            public Inliner<K, V, M> putAll(final Map<K, V> values) {
                map.putAll(values);
                return this;
            }

            public Inliner<K, V, M> remove(final Object value) {
                map.remove(value);
                return this;
            }

            public M map() {
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
        <K2, V2> Map.Immutable<K2, V2> mapEntries(Function<? super K, ? extends K2> keyFunction, Function<? super V, ? extends V2> valueFunction);

        @CheckReturnValue
        @Nonnull
        default Map.Immutable<K, V> replace(K key, V expectedValue, V newValue) {
            return Objects.equals(expectedValue, this.get(key))
                    ? this.without(key).with(key, newValue)
                    : this;
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
        boolean replace(K key, V expectedValue, V newValue);

        @Override
        Map.Concurrent<K, V> mutableCopy();

        @Override
        Set.Concurrent<K> keys();

        @Override
        Set.Concurrent<? extends Map.Mutable.Entry<K, V>> entries();

    }

}
