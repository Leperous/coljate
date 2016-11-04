package net.coljate.map;

import java.util.Objects;
import java.util.Optional;

import net.coljate.Collection;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public interface Map<K, V> extends Collection<Map.Entry<K, V>> {

    Lookup<V> lookup(Object key);

    default V get(final Object key) {
        return this.lookup(key).value();
    }

    default Optional<V> maybeGet(final Object key) {
        return Optional.ofNullable(this.get(key));
    }

    Set<K> keys();

    Collection<V> values();

    @Deprecated
    default boolean contains(final Object object) {
        return object instanceof Map.Entry
                && this.contains((Map.Entry) object);
    }

    default boolean contains(final Map.Entry<?, ?> entry) {
        final Lookup<V> checked = this.lookup(entry.key());
        return checked.contained() && Objects.equals(entry.value(), checked.value());
    }

    default boolean containsKey(final Object key) {
        return this.keys().contains(key);
    }

    default boolean containsValue(final Object value) {
        return this.values().contains(value);
    }

    @Override
    MutableMap<K, V> mutableCopy();

    @Override
    ImmutableMap<K, V> immutableCopy();

    interface Entry<K, V> {

        K key();

        V value();

    }

    interface Lookup<V> {

        boolean contained();

        V value();

    }

}
