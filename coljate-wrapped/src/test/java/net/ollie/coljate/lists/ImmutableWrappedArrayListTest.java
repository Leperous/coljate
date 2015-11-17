package net.ollie.coljate.lists;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableWrappedArrayListTest extends ImmutableListTest {

    @Override
    protected ImmutableList<Object> create(final Object... objects) {
        return ImmutableWrappedArrayList.copyOf(objects);
    }

}
