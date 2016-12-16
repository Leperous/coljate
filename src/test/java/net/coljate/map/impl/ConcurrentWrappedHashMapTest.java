package net.coljate.map.impl;

import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.map.ConcurrentMapTest;
import net.coljate.map.Entry;
import net.coljate.map.ObjectMapTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ConcurrentWrappedHashMapTest
        extends ObjectMapTest
        implements ConcurrentMapTest<Object, Object>, AllCollectionSizeTest<Entry<Object, Object>> {

    @Override
    public ConcurrentWrappedHashMap<Object, Object> create(final java.util.List<Entry<Object, Object>> entries) {
        return ConcurrentWrappedHashMap.copyOf(entries);
    }

}
