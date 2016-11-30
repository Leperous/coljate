package net.coljate.list.impl;

import net.coljate.list.MutableArrayTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedArrayListTest extends MutableArrayTest {

    @Override
    protected <T> MutableWrappedArrayList<T> create(final T... elements) {
        return MutableWrappedArrayList.copyOf(elements);
    }

}
