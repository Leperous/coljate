package net.ollie.coljate.list;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableWrappedArrayListTest extends ImmutableListTest {

    @Override
    public ImmutableList<Object> createFrom(final Object... objects) {
        return ImmutableWrappedArrayList.copyOf(objects);
    }

}
