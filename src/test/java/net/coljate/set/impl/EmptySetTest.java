package net.coljate.set.impl;

import net.coljate.set.ImmutableSet;
import net.coljate.set.ImmutableSetTest;

import org.junit.Assume;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptySetTest extends ImmutableSetTest {

    @Override
    protected <T> ImmutableSet<T> create(T... elements) {
        Assume.assumeTrue(elements.length == 0);
        return EmptySet.instance();
    }

}
