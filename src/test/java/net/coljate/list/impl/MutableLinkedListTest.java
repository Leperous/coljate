package net.coljate.list.impl;

import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.collection.ObjectCollectionTest;
import net.coljate.list.MutableListTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableLinkedListTest
        extends ObjectCollectionTest
        implements MutableListTest<Object>, AllCollectionSizeTest<Object> {

    @Override
    public MutableLinkedList<Object> create(final java.util.List<Object> elements) {
        return MutableLinkedList.copyOf(elements);
    }

}
