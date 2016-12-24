package net.coljate.list.impl;

import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.collection.ObjectCollectionTest;
import net.coljate.list.MutableArrayTest;

import org.junit.jupiter.api.Disabled;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Disabled
public class MutableAtomicArrayTest
        extends ObjectCollectionTest
        implements MutableArrayTest<Object>, AllCollectionSizeTest<Object> {

    @Override
    public MutableAtomicArray<Object> create(final java.util.List<Object> elements) {
        return MutableAtomicArray.copyOf(elements);
    }

}
