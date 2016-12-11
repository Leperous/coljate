package net.coljate.list;

import net.coljate.collection.CollectionTest;

/**
 *
 * @author Ollie
 */
public interface ListTest<T> extends CollectionTest<T> {

    @Override
    List<T> create(java.util.List<T> elements);

    @Override
    default List<T> create(final T element) {
        return this.create(singletonList(element));
    }

}
