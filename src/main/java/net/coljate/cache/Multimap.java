package net.coljate.cache;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.util.iterator.CovariantIterator;

/**
 *
 * @author ollie
 */
public interface Multimap<K, V> extends Cache<K, Collection<V>> {

    @Override
    MultimapEntry<K, V, ? extends Collection<V>> getEntry(Object key);

    @Override
    CovariantIterator<Entry<K, Collection<V>>, ?> iterator();

    @Override
    ImmutableMultimap<K, V> immutableCopy();

    interface MultimapEntry<K, V, C extends Collection<V>> extends Entry<K, Collection<V>> {

        @Override
        C value();

    }

}
