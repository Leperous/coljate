package net.coljate.map.impl;

import org.junit.Assume;

import net.coljate.collection.EmptyCollectionTest;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableMapTest;
import net.coljate.map.ObjectMapTest;

/**
 *
 * @author Ollie
 */
public class EmptyMapTest
        extends ObjectMapTest
        implements ImmutableMapTest<Object, Object>, EmptyCollectionTest<Entry<Object, Object>> {

    @Override
    public EmptyMap<Object, Object> createEmpty() {
        return EmptyMap.instance();
    }

    @Override
    public EmptyMap<Object, Object> create(final Entry<Object, Object>... elements) {
        Assume.assumeTrue(elements.length == 0);
        return this.createEmpty();
    }

}
