package net.coljate.list;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableArrayListTest extends MutableListTest {

    @Override
    protected <T> MutableArrayList<T> create(final T... elements) {
        return MutableArrayList.copyOf(elements);
    }

}
