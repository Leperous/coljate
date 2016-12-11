package net.coljate.map;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public interface ConcurrentMapTest<K, V> extends MutableMapTest<K, V> {

    @Override
    ConcurrentMap<K, V> create(java.util.List<Entry<K, V>> entries);

}
