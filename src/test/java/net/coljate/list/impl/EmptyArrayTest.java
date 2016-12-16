package net.coljate.list.impl;

import net.coljate.collection.EmptyCollectionTest;
import net.coljate.collection.ObjectCollectionTest;
import net.coljate.list.ImmutableArrayTest;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptyArrayTest
        extends ObjectCollectionTest
        implements EmptyCollectionTest<Object>, ImmutableArrayTest<Object> {

    @Override
    public EmptyArray<Object> create(final java.util.List<Object> elements) {
        assumeTrue(elements.isEmpty());
        return this.create();
    }

    @Override
    public EmptyArray<Object> create() {
        return EmptyArray.instance();
    }

}
