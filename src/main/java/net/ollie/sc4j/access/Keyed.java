package net.ollie.sc4j.access;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.Map;
import net.ollie.sc4j.imposed.Distinctness.Unique;

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
        V get(@Nonnull Object key);

        @Override
        Unique<K> keys();

    }

    interface Dual<K1, K2, V> extends Keyed.Single<Map.Entry<K1, K2>, V> {

        V get(Object key1, Object key2);

        default V get(final Map.Entry<? extends K1, ? extends K2> entry) {
            return this.get(entry.key(), entry.value());
        }

        @Override
        default V get(final Object object) {
            if (object instanceof Map.Entry) {
                final Map.Entry<?, ?> that = (Map.Entry) object;
                return this.get(that.key(), that.value());
            } else if (object instanceof java.util.Map) {
                final java.util.Map.Entry<?, ?> that = (java.util.Map.Entry) object;
                return this.get(that.getKey(), that.getValue());
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
