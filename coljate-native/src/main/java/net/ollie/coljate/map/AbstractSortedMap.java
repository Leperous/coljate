package net.ollie.coljate.map;

import net.ollie.coljate.set.SortedSet;
import net.ollie.coljate.theory.Finite;

/**
 *
 * @author Ollie
 */
public abstract class AbstractSortedMap<K, V> implements SortedMap<K, V> {

    private transient SortedSet<K> keys;

    @Override
    public SortedSet<K> keys() {
        return keys == null ? keys = new SortedMapKeySet<>(this) : keys;
    }

    @Override
    public abstract boolean containsKey(Object key);

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + Finite.toString(this);
    }

}
