package net.ollie.coljate.list;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableWrappedArrayListTest extends ImmutableListTest {

    @Override
    protected ImmutableList<Object> createFrom(final Object... objects) {
        return ImmutableWrappedArrayList.copyOf(objects);
    }

}
