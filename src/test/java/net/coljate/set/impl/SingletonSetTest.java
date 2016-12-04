package net.coljate.set.impl;

import net.coljate.set.ImmutableSetTest;

import org.junit.Assume;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SingletonSetTest extends ImmutableSetTest {

    @Override
    protected <T> SingletonSet<T> create(final T... elements) {
        Assume.assumeTrue(elements.length == 1);
        return SingletonSet.of(elements[0]);
    }

}
