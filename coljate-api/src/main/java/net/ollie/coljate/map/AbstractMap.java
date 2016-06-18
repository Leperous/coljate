package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    @Override
    public boolean equals(final Object that) {
        return that instanceof Map
                && Map.equal(this, (Map) that);
    }

    @Override
    public int hashCode() {
        return Map.hash(this);
    }

    @Override
    public String toString() {
        return Map.toString(this);
    }

}
