package net.ollie.coljate.maps;

import java.util.Objects;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.imposed.Cached;
import net.ollie.coljate.imposed.Invertible;
import net.ollie.coljate.imposed.Invertible.Surjective;
import net.ollie.coljate.imposed.Mutability;
import net.ollie.coljate.sets.Set;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * A many-to-one mapping from unique keys.
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
    default V get(K key) {
        return this.maybeGet(key);
    }

    @Override
    MultiMap<V, K> inverseCopy();

    @CheckForNull
    V maybeGet(Object key);

    @Override
    Set<K> keys();

    Set<? extends Entry<K, V>> entries();

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

    interface Entry<K, V> extends Invertible<K, V> {

        @Nonnull
        K key();

        @CheckForNull
        V value();

        @Override
        Entry<V, K> inverseCopy();

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

        @CheckForNull
        default boolean remove(final Object key, final Object value) {
            final Object current = this.maybeGet(key);
            if (Objects.equals(current, value) && this.containsKey(key)) {
                this.remove(key);
                return true;
            } else {
                return false;
            }
        }

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
        Streamable.Immutable<V> values();

        @Override
        Set.Immutable<? extends Map.Immutable.Entry<K, V>> entries();

        @Override
        Map.Immutable<K, V> with(K key, V value);

        @Override
        Map.Immutable<K, V> without(Object key);

        @CheckReturnValue
        @Nonnull
        default Map.Immutable<K, V> replace(K key, V expectedValue, V newValue) {
            return Objects.equals(expectedValue, this.get(key))
                    ? this.without(key).with(key, newValue)
                    : this;
        }

        @Override
        MultiMap.Immutable<V, K> inverseCopy();

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
    interface ThreadSafe<K, V>
            extends Map.Mutable<K, V> {

        @Override
        V putIfAbsent(K key, V value);

        @Override
        boolean replace(K key, V expectedValue, V newValue);

        @Override
        boolean remove(Object key, Object value);

        @Override
        Map.ThreadSafe<K, V> mutableCopy();

        @Override
        Set.Concurrent<K> keys();

        @Override
        Set.Concurrent<? extends Map.Mutable.Entry<K, V>> entries();

    }

    abstract class Abstract<K, V> implements Map<K, V> {

        @Override
        public String toString() {
            return this.entries().toString();
        }

        @Override
        public boolean equals(final Object object) {
            return object instanceof Map && this.equals((Map) object);
        }

        @Override
        public int hashCode() {
            return this.hash();
        }

    }

}
