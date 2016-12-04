package net.coljate.list.impl;

import net.coljate.list.MutableArrayTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableAtomicArrayTest extends MutableArrayTest {

    @Override
    protected <T> MutableAtomicArray<T> create(final T... elements) {
        return MutableAtomicArray.copyOf(elements);
    }

}
