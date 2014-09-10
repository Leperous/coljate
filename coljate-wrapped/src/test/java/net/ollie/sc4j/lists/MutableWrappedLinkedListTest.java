package net.ollie.sc4j.lists;

import net.ollie.sc4j.AbstractMutableListTest;
import net.ollie.sc4j.utils.Arrays;

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
