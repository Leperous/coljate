package net.coljate.list;

import net.coljate.list.impl.MutableWrappedList;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedListTest extends MutableListTest {

    @Override
    protected <T> MutableWrappedList<T> create(final T... elements) {
        return MutableWrappedList.createArrayList(elements);
    }

}
