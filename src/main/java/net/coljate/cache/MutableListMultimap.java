package net.coljate.cache;

import net.coljate.cache.impl.MutableCacheBackedListMultimap;
import net.coljate.list.MutableList;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 */
public interface MutableListMultimap<K, V>
        extends ListMultimap<K, V>, MutableMultimap<K, V> {

    @Override
    MutableMultimapEntry<K, V, ? extends MutableList<V>> getEntry(Object key);

    @Override
    default MutableList<V> get(Object key) {
        return Functions.ifNonNull(this.getEntry(key), MultimapEntry::value);
    }

    static <K, V> MutableListMultimap<K, V> createLinkedListMultimap() {
        return MutableCacheBackedListMultimap.createLinkedListMultimap();
    }

}
