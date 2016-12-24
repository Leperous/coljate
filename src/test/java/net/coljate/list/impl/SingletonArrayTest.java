package net.coljate.list.impl;

import net.coljate.collection.ObjectCollectionTest;
import net.coljate.collection.SingletonCollectionTest;
import net.coljate.list.ImmutableArrayTest;

import org.junit.jupiter.api.Assumptions;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SingletonArrayTest
        extends ObjectCollectionTest
        implements ImmutableArrayTest<Object>, SingletonCollectionTest<Object> {

    @Override
    public SingletonArray<Object> create(final java.util.List<Object> elements) {
        Assumptions.assumeTrue(elements.size() == 1);
        return SingletonArray.of(elements.get(0));
    }

}
