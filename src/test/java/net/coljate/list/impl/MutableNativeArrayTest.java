package net.coljate.list.impl;

import net.coljate.list.MutableArrayTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableNativeArrayTest extends MutableArrayTest {

    @Override
    protected <T> MutableNativeArray<T> create(final T... elements) {
        return MutableNativeArray.copyOf(elements);
    }

}
