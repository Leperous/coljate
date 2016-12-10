package net.coljate.list.impl;

import net.coljate.list.MutableListTest;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableLinkedListTest extends MutableListTest {

    @Override
    protected <T> MutableLinkedList<T> create(final T... elements) {
        return MutableLinkedList.copyOf(elements);
    }

}
