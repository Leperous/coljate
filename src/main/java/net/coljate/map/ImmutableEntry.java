package net.coljate.map;

import java.util.Objects;

/**
 *
 * @author ollie
 */
public class ImmutableEntry<K, V>
        implements Entry<K, V> {

    private final K key;
    private final V value;

    public ImmutableEntry(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public V value() {
        return value;
    }

    @Override
    @Deprecated
    public ImmutableEntry<K, V> immutableCopy() {
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof ImmutableEntry
                && this.equals((ImmutableEntry) object);
    }

    protected boolean equals(final ImmutableEntry<K, V> that) {
        return Objects.equals(this.key, that.key)
                && Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.key);
        hash = 89 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":[" + key + '=' + value + ']';
    }

}
