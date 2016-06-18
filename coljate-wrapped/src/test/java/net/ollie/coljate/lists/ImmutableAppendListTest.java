package net.ollie.coljate.lists;

import net.ollie.coljate.list.ImmutableListTest;

import java.util.Arrays;

import net.ollie.coljate.list.ImmutableAppendList;
import net.ollie.coljate.list.ImmutableList;
import net.ollie.coljate.list.ImmutableWrappedArrayList;
import net.ollie.coljate.list.ImmutableWrappedEmptyList;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableAppendListTest extends ImmutableListTest {

    @Override
    protected ImmutableList<Object> empty() {
        return ImmutableWrappedEmptyList.empty();
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
                return ImmutableAppendList.of(ImmutableWrappedArrayList.copyOf(sublist), objects[objects.length - 1]);
        }
    }

}
