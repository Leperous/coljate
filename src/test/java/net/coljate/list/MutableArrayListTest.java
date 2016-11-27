package net.coljate.list;

import net.coljate.list.impl.MutableNativeArray;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableArrayListTest extends MutableListTest {

    @Override
    protected <T> MutableNativeArray<T> create(final T... elements) {
        return MutableNativeArray.copyOf(elements);
    }

}
