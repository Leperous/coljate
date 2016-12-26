package net.coljate.cache;

import net.coljate.list.List;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 */
public interface ListMultimap<K, V>
        extends Multimap<K, V> {

    @Override
    MultimapEntry<K, V, ? extends List<V>> getEntry(final Object key);

    @Override
    default List<V> get(final Object key) {
        return Functions.ifNonNull(this.getEntry(key), MultimapEntry::value);
    }

    @Override
    MutableListMultimap<K, V> mutableCopy();

    @Override
    ImmutableListMultimap<K, V> immutableCopy();

}
