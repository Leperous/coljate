package net.ollie.sc4j.maps;

import net.ollie.sc4j.Map;
import net.ollie.sc4j.utils.Conditions;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/**
 *
 * @author Ollie
 */
public abstract class Entries<K, V>
        implements Map.Entry<K, V> {

    private final K key;

    protected Entries(final K key) {
        this.key = Conditions.checkNotNull(key);
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public Map.Mutable.Entry<K, V> mutableCopy() {
        return new MutableEntry<>(this.key(), this.value());
    }

    @Override
    public Map.Immutable.Entry<K, V> immutableCopy() {
        return new ImmutableEntry<>(this.key(), this.value());
    }

    @NotThreadSafe
    public static class MutableEntry<K, V>
            extends Entries<K, V>
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

    }

    @ThreadSafe
    public static class ThreadSafeEntry<K, V>
            extends Entries<K, V>
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

    }

    public static class ImmutableEntry<K, V>
            extends Entries<K, V>
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
