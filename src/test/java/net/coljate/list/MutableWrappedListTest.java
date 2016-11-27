package net.coljate.list;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedListTest extends MutableListTest {

    @Override
    protected <T> MutableWrappedList<T> create(final T... elements) {
        return MutableWrappedList.createArrayList(elements);
    }

}
