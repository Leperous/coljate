package net.ollie.coljate.list;

import net.ollie.coljate.lists.ImmutableListTest;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableArrayListTest extends ImmutableListTest {

    @Override
    protected ImmutableList<Object> create(final Object... objects) {
        return ImmutableArrayList.of(objects);
    }

}
