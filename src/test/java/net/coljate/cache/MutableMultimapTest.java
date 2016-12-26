package net.coljate.cache;

import net.coljate.collection.Collection;

/**
 *
 * @author ollie
 */
public interface MutableMultimapTest<K, V>
        extends MultimapTest<K, V>, MutableCacheTest<K, Collection<V>> {

    @Override
    MutableMultimap<K, V> createTestCollection();

    interface ZeroEntryTests<K, V>
            extends MutableMultimapTest<K, V>, MultimapTest.ZeroEntryTests<K, V>, MutableCacheTest.ZeroEntryTests<K, Collection<V>> {

    }

}
