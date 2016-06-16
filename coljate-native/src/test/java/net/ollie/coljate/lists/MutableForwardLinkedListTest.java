package net.ollie.coljate.lists;

import net.ollie.coljate.list.MutableList;

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
