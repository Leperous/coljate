package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
public abstract class AbstractKeyValue<K, V> implements KeyValue<K, V> {

    @Override
    public int hashCode() {
        return KeyValue.hashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof KeyValue && KeyValue.equal(this, (KeyValue) obj);
    }

    @Override
    public String toString() {
        return KeyValue.toString(this);
    }

}
