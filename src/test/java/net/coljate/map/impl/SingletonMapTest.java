package net.coljate.map.impl;

import org.junit.Assume;

import net.coljate.collection.SingletonCollectionTest;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableMapTest;
import net.coljate.map.ObjectMapTest;

/**
 *
 * @author Ollie
 */
public class SingletonMapTest
        extends ObjectMapTest
        implements ImmutableMapTest<Object, Object>, SingletonCollectionTest<Entry<Object, Object>> {

    @Override
    public SingletonMap<Object, Object> create(final java.util.List<Entry<Object, Object>> elements) {
        Assume.assumeTrue(elements.size() == 1);
        return this.create(elements.get(0));
    }

    @Override
    public SingletonMap<Object, Object> create(final Entry<Object, Object> element) {
        return SingletonMap.of(element.key(), element.value());
    }

}
