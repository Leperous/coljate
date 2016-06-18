package net.ollie.coljate.list;

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
