package net.ollie.coljate.maps;

import net.ollie.coljate.Map;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class ImmutableMapEntry<K, V> extends AbstractMapEntry<K, V> implements Map.Immutable.Entry<K, V> {

    @SuppressWarnings("unchecked")
    @Nonnull
    public static <K, V> Map.Immutable.Entry<K, V> copy(final Map.Entry<? extends K, ? extends V> entry) {
        return entry instanceof Map.Immutable.Entry
                ? (Map.Immutable.Entry<K, V>) entry
                : new ImmutableMapEntry<>(entry.key(), entry.value());
    }

    @Nonnull
    public static <K, V> Map.Immutable.Entry<K, V> copy(final java.util.Map.Entry<? extends K, ? extends V> entry) {
        return new ImmutableMapEntry<>(entry.getKey(), entry.getValue());
    }

    private final V value;

    public ImmutableMapEntry(final K key, final V value) {
        super(key);
        this.value = value;
    }

    @Override
    public V value() {
        return value;
    }

    @Override
    public Map.Immutable.Entry<K, V> immutableCopy() {
        return this;
    }

}
