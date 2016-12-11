package net.coljate.map.impl;

import net.coljate.NamedObject;
import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.map.MapTest;
import net.coljate.map.ObjectMapTest;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class RepeatedValueMapTest
        extends ObjectMapTest
        implements MapTest<Object, Object>, AllCollectionSizeTest<Entry<Object, Object>> {

    private final Object value = new NamedObject("value");

    @Override
    public Entry<Object, Object> createObject() {
        return new ImmutableEntry<>(new Object(), value);
    }

    @Override
    public RepeatedValueMap<Object, Object> create(final java.util.List<Entry<Object, Object>> elements) {
        final java.util.List<Object> keys = new java.util.ArrayList<>(elements.size());
        elements.forEach(entry -> keys.add(entry.key()));
        return RepeatedValueMap.copyOf(keys, value);
    }

}
