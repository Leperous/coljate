package net.ollie.sc4j.sets;

import java.util.Arrays;

import net.ollie.sc4j.AbstractImmutableSetTest;
import net.ollie.sc4j.Set;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedHashSetTest
        extends AbstractImmutableSetTest {

    @Override
    protected Set.Immutable<Object> create(final Object... objects) {
        return ImmutableWrappedHashSet.copy(Arrays.asList(objects));
    }

}
