package net.ollie.coljate.lists;

import net.ollie.coljate.lists.MutableWrappedLinkedList;
import net.ollie.coljate.AbstractMutableListTest;
import net.ollie.coljate.utils.Arrays;

/**
 *
 * @author Ollie
 */
public class MutableWrappedLinkedListTest
        extends AbstractMutableListTest<MutableWrappedLinkedList<Object>> {

    @Override
    protected MutableWrappedLinkedList<Object> create(final Object... objects) {
        return MutableWrappedLinkedList.copy(Arrays.asList(objects));
    }

}
