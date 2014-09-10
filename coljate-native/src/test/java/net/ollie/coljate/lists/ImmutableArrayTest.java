package net.ollie.coljate.lists;

import net.ollie.coljate.AbstractImmutableArrayTest;
import net.ollie.coljate.Array;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class ImmutableArrayTest extends AbstractImmutableArrayTest {

    @Override
    protected Array.Immutable<Object> create(final Object... objects) {
        return ImmutableArray.copy(objects);
    }

}
