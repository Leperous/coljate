package net.coljate.set.impl;

import net.coljate.set.MutableSetTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedHashSetTest extends MutableSetTest {

    @Override
    protected <T> MutableWrappedHashSet<T> create(final T... elements) {
        return MutableWrappedHashSet.copyOf(elements);
    }

}
