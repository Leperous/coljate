package net.coljate.map.impl;

import net.coljate.collection.EmptyCollectionTest;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableMapTest;
import net.coljate.map.ObjectMapTest;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.opentest4j.TestAbortedException;

/**
 *
 * @author Ollie
 */
public class EmptyMapTest
        extends ObjectMapTest
        implements ImmutableMapTest<Object, Object>, EmptyCollectionTest<Entry<Object, Object>> {

    @Override
    public EmptyMap<Object, Object> create() {
        return EmptyMap.instance();
    }

    @Override
    public EmptyMap<Object, Object> create(final Entry<Object, Object> element) {
        throw new TestAbortedException();
    }

    @Override
    public EmptyMap<Object, Object> create(final java.util.List<Entry<Object, Object>> elements) {
        assumeTrue(elements.isEmpty());
        return this.create();
    }

}
