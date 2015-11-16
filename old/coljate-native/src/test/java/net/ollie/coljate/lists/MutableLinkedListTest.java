package net.ollie.coljate.lists;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class MutableLinkedListTest extends AbstractMutableListTest<MutableLinkedList<Object>> {

    @Override
    protected MutableLinkedList<Object> create(final Object... objects) {
        return MutableLinkedList.create(objects);
    }

}
