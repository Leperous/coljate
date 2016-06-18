package net.ollie.coljate.list;

import net.ollie.coljate.lists.MutableListTest;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableArrayListTest extends MutableListTest {

    @Override
    protected MutableList<Object> create(final Object... objects) {
        return MutableArrayList.copyOf(objects);
    }

}
