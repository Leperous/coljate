package net.coljate.list.impl;

import net.coljate.list.ImmutableArray;
import net.coljate.list.ImmutableArrayTest;

import org.junit.Assume;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableSingletonArrayTest extends ImmutableArrayTest {

    @Override
    protected <T> ImmutableArray<T> create(final T... elements) {
        Assume.assumeTrue(elements.length == 1);
        return ImmutableSingletonArray.of(elements[0]);
    }

}
