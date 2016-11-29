package net.coljate.list.impl;

import net.coljate.list.ImmutableArrayTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableNativeArrayTest extends ImmutableArrayTest {

    @Override
    protected <T> ImmutableNativeArray<T> create(final T... elements) {
        return ImmutableNativeArray.copyOf(elements);
    }

}
