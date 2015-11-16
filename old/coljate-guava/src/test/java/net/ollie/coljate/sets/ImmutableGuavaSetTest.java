package net.ollie.coljate.sets;

import net.ollie.coljate.sets.ImmutableGuavaSet;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class ImmutableGuavaSetTest extends AbstractImmutableSetTest {

    @Override
    protected Set.Immutable<Object> create(final Object... objects) {
        return ImmutableGuavaSet.copy(objects);
    }

}
