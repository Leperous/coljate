package net.coljate.list;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableArrayListTest extends MutableListTest {

    @Override
    protected <T> MutableNativeArray<T> create(final T... elements) {
        return MutableNativeArray.copyOf(elements);
    }

}
