package net.coljate.map.impl;

import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.map.Entry;
import net.coljate.map.MutableMapTest;
import net.coljate.map.ObjectMapTest;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedHashMapTest
        extends ObjectMapTest
        implements MutableMapTest<Object, Object>, AllCollectionSizeTest<Entry<Object, Object>> {

    @Override
    public MutableWrappedHashMap<Object, Object> create(final java.util.List<Entry<Object, Object>> entries) {
        return MutableWrappedHashMap.copyOf(entries);
    }

}
