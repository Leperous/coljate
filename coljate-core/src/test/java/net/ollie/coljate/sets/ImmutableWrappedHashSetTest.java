package net.ollie.coljate.sets;

import java.util.Arrays;

import net.ollie.coljate.AbstractImmutableSetTest;
import net.ollie.coljate.Set;

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
