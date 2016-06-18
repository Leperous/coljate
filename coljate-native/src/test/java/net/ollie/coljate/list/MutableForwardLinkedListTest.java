package net.ollie.coljate.list;

import net.ollie.coljate.lists.MutableListTest;

/**
 *
 * @author ollie
 */
public class MutableForwardLinkedListTest extends MutableListTest {

    @Override
    protected MutableList<Object> create(final Object... objects) {
        return MutableForwardLinkedList.copyOf(objects);
    }

}
