package net.ollie.coljate.list;

import java.util.Arrays;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableAppendListTest extends ImmutableListTest {

    @Override
    protected ImmutableList<Object> create() {
        return ImmutableWrappedEmptyArray.empty();
    }

    @Override
    protected ImmutableList<Object> createFrom(final Object... objects) {
        switch (objects.length) {
            case 0:
                return create();
            case 1:
                return ImmutableAppendList.of(create(), objects[0]);
            default:
                final Object[] sublist = Arrays.copyOf(objects, objects.length - 1);
                return ImmutableAppendList.of(ImmutableWrappedArrayList.copyOf(sublist), objects[objects.length - 1]);
        }
    }

}
