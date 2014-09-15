package net.ollie.coljate.lists;

import net.ollie.coljate.lists.ImmutableGuavaList;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class ImmutableGuavaListTest extends AbstractImmutableArrayTest {

    @Override
    protected Array.Immutable<Object> create(final Object... objects) {
        return ImmutableGuavaList.copy(objects);
    }

}
