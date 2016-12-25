package net.coljate.cache;

import net.coljate.collection.Collection;
import net.coljate.collection.MutableCollection;
import net.coljate.map.Entry;
import net.coljate.map.MutableEntry;
import net.coljate.map.MutableMap;
import net.coljate.map.impl.MapIterator;
import net.coljate.util.iterator.CovariantIterator;

/**
 *
 * @author ollie
 */
public interface MutableMultimap<K, V>
        extends Multimap<K, V>, MutableMap<K, Collection<V>> {

    @Override
    MutableMultimapEntry<K, V, ?> getEntry(Object key);

    @Override
    ImmutableMultimap<K, V> immutableCopy();

    @Override
    default CovariantIterator<Entry<K, Collection<V>>, ? extends MutableEntry<K, Collection<V>>> iterator() {
        return new MapIterator<>(this.keys(), this::getEntry);
    }

    interface MutableMultimapEntry<K, V, C extends MutableCollection<V>>
            extends MutableEntry<K, Collection<V>>, MultimapEntry<K, V, C> {

    }

}
