package net.ollie.coljate.maps;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/**
 *
 * @author Ollie
 */
public abstract class MapEntry<K, V>
        implements Map.Entry<K, V> {

    public static <K, V> ImmutableEntry<K, V> immutable(final K key, final V value) {
        return new ImmutableEntry<>(key, value);
    }

    public static <K, V> MutableEntry<K, V> mutable(final K key, final V value) {
        return new MutableEntry<>(key, value);
    }

    public static <K, V> Map.Immutable.Entry<K, V> copy(final java.util.Map.Entry<? extends K, ? extends V> entry) {
        return new ImmutableEntry<>(entry.getKey(), entry.getValue());
    }

    private final K key;

    protected MapEntry(final K key) {
        this.key = key;
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public Map.Entry<V, K> inverse() {
        return new ImmutableEntry<>(this.value(), this.key());
    }

    @Override
    public Map.Mutable.Entry<K, V> mutableCopy() {
        return new MutableEntry<>(this.key(), this.value());
    }

    @Override
    public Map.Immutable.Entry<K, V> immutableCopy() {
        return new ImmutableEntry<>(this.key(), this.value());
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Map.Entry && this.equals((Map.Entry) object);
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

    @Override
    public String toString() {
        return this.key() + "=" + this.value();
    }

    @NotThreadSafe
    public static class MutableEntry<K, V>
            extends MapEntry<K, V>
            implements Map.Mutable.Entry<K, V> {

        private V value;

        public MutableEntry(final K key, final V value) {
            super(key);
            this.value = value;
        }

        @Override
        public void setValue(final V value) {
            this.value = value;
        }

        @Override
        public V value() {
            return value;
        }

        @Override
        public Map.Entry<V, K> inverse() {
            return new MutableEntry<>(this.value(), this.key());
        }

    }

    @ThreadSafe
    public static class ThreadSafeEntry<K, V>
            extends MapEntry<K, V>
            implements Map.Mutable.Entry<K, V> {

        private volatile V value;

        public ThreadSafeEntry(final K key, final V value) {
            super(key);
            this.value = value;
        }

        @Override
        public V value() {
            return value;
        }

        @Override
        public void setValue(final V value) {
            this.value = value;
        }

        @Override
        public Map.Entry<V, K> inverse() {
            return new ThreadSafeEntry<>(this.value(), this.key());
        }

    }

    @Immutable
    public static class ImmutableEntry<K, V>
            extends MapEntry<K, V>
            implements Map.Immutable.Entry<K, V> {

        private final V value;

        public ImmutableEntry(final K key, final V value) {
            super(key);
            this.value = value;
        }

        @Override
        public V value() {
            return value;
        }

    }

}
