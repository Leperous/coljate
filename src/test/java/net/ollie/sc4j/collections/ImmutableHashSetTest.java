package net.ollie.sc4j.collections;

import java.util.Arrays;

import net.ollie.sc4j.AbstractImmutableSetTest;
import net.ollie.sc4j.Set;

/**
 *
 * @author Ollie
 */
public class ImmutableHashSetTest
        extends AbstractImmutableSetTest {

    @Override
    protected Set.Immutable<Object> create(final Object... objects) {
        return ImmutableSet.copyIntoHashSet(Arrays.asList(objects));
    }

}
