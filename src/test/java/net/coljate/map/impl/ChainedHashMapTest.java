package net.coljate.map.impl;

import net.coljate.map.Entry;
import net.coljate.map.MutableMapTest;
import net.coljate.map.ObjectMapTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ChainedHashMapTest
        extends ObjectMapTest
        implements MutableMapTest<Object, Object> {

    @Override
    public ChainedHashMap<Object, Object> create(final java.util.List<Entry<Object, Object>> entries) {
        return ChainedHashMap.copyOf(entries);
    }

}
