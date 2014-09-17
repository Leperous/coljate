package net.ollie.coljate.access;

import java.util.Optional;

import net.ollie.coljate.Collection;
import net.ollie.coljate.imposed.Distinctness.Unique;
import net.ollie.coljate.maps.Map;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * An element {@code V} can be accessed given one or more keys.
 *
 * @author Ollie
 */
public interface Keyed<V> {

    @Nonnull
    Unique<?> keys();

    default boolean containsKey(final Object key) {
        return this.keys().contains(key);
    }

    @Nonnull
    Collection<V> values();

    default boolean containsValue(final V value) {
        return this.values().contains(value);
    }

    default boolean isEmpty() {
        return this.keys().isEmpty() || this.values().isEmpty();
    }

    interface Single<K, V> extends Keyed<V> {

        @CheckForNull
        default V get(@Nonnull final K key) {
            return this.maybeGet(key);
        }

        @CheckForNull
        V maybeGet(Object key);

        @Nonnull
        default Optional<V> tryGet(final K key) {
            return Optional.ofNullable(this.get(key));
        }

        @Nonnull
        default V getOrDefault(final K key, @Nonnull final V defaultValue) {
            final V value = this.get(key);
            return value == null ? defaultValue : value;
        }

        @Override
        Unique<K> keys();

    }

    interface Typed<V> extends Single<Key<?>, V> {

        <K extends Key<T>, T extends V> T get(K key);

    }

    interface Key<T> {

    }

    interface Dual<K1, K2, V> extends Keyed.Single<Map.Entry<K1, K2>, V> {

        @CheckForNull
        default V get(final K1 key1, final K2 key2) {
            return this.maybeGet(key1, key2);
        }

        @CheckForNull
        V maybeGet(Object key1, Object key2);

        @Override
        @CheckForNull
        default V get(final Map.Entry<K1, K2> entry) {
            return this.get(entry.key(), entry.value());
        }

        @CheckForNull
        default V getAny(final Map.Entry<? extends K1, ? extends K2> entry) {
            return this.get(entry.key(), entry.value());
        }

        @Override
        default V maybeGet(final Object object) {
            if (object instanceof Map.Entry) {
                final Map.Entry<?, ?> that = (Map.Entry) object;
                return this.maybeGet(that.key(), that.value());
            } else if (object instanceof java.util.Map) {
                final java.util.Map.Entry<?, ?> that = (java.util.Map.Entry) object;
                return this.maybeGet(that.getKey(), that.getValue());
            } else {
                return null;
            }
        }

    }

    interface Multiple<K, V> extends Keyed<V> {

        @Override
        Unique<K> keys();

        @CheckForNull
        Collection<V> get(@Nonnull Object key);

    }

}
