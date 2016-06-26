package net.ollie.coljate.list;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableArrayListTest extends MutableListTest {

    @Override
    public MutableList<Object> createFrom(final Object... objects) {
        return MutableWrappedArrayList.copyOf(objects);
    }

}
