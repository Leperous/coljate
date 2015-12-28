package net.ollie.coljate.lists;

/**
 *
 * @author ollie
 */
public class MutableLinkedListTest extends MutableListTest {

    @Override
    protected MutableList<Object> create(final Object... objects) {
        return MutableForwardLinkedList.copyOf(objects);
    }

}
