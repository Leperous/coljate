package net.coljate.map;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public interface LoadingCacheTest<K, V> extends MapTest<K, V> {

    @Override
    LoadingCache<K, V> create(java.util.List<Entry<K, V>> entries);

}
