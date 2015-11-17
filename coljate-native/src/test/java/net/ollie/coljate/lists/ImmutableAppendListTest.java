package net.ollie.coljate.lists;

import java.util.Arrays;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableAppendListTest extends ImmutableListTest {

    @Override
    protected ImmutableList<Object> empty() {
        return ImmutableEmptyList.instance();
    }

    @Override
    protected ImmutableList<Object> create(final Object... objects) {
        switch (objects.length) {
            case 0:
                return empty();
            case 1:
                return ImmutableAppendList.of(empty(), objects[0]);
            default:
                final Object[] sublist = Arrays.copyOf(objects, objects.length - 1);
                return ImmutableAppendList.of(ImmutableArrayList.copyOf(sublist), objects[objects.length - 1]);
        }
    }

}
