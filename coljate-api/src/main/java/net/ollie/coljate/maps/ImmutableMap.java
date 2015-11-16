package net.ollie.coljate.maps;

import net.ollie.coljate.ImmutableCollection;
import net.ollie.coljate.sets.ImmutableSet;

/**
 *
 * @author Ollie
 */
public interface ImmutableMap<K, V> extends Map<K, V> {

    @Override
    ImmutableSet<K> keys();

    @Override
    ImmutableSet<? extends ImmutableMapEntry<K, V>> entries();

    @Override
    ImmutableCollection<V> values();

    @Override
    @Deprecated
    default ImmutableMap<K, V> immutableCopy() {
        return this;
    }

}
