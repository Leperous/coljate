package net.coljate.cache;

import net.coljate.collection.Collection;
import net.coljate.collection.ImmutableCollection;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.map.ImmutableMap;
import net.coljate.util.Functions;
import net.coljate.util.iterator.UnmodifiableCovariantIterator;

/**
 *
 * @author ollie
 */
public interface ImmutableMultimap<K, V>
        extends Multimap<K, V>, ImmutableMap<K, Collection<V>> {

    @Override
    ImmutableMultimapEntry<K, V, ?> getEntry(Object key);

    @Override
    default ImmutableCollection<V> get(Object key) {
        return Functions.ifNonNull(this.getEntry(key), ImmutableMultimapEntry::value);
    }

    @Override
    UnmodifiableCovariantIterator<Entry<K, Collection<V>>, ?> iterator();

    @Override
    @Deprecated
    default ImmutableMultimap<K, V> immutableCopy() {
        return this;
    }

    interface ImmutableMultimapEntry<K, V, C extends ImmutableCollection<V>>
            extends MultimapEntry<K, V, C>, ImmutableEntry<K, Collection<V>> {

        @Override
        C value();
        
    }

}
