package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMapEntry<K, V> implements MapEntry<K, V> {

    @Override
    public int hashCode() {
        return MapEntry.hashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof MapEntry && MapEntry.equal(this, (MapEntry) obj);
    }

    @Override
    public String toString() {
        return MapEntry.toString(this);
    }

}
