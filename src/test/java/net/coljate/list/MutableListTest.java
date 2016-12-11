package net.coljate.list;

import net.coljate.collection.MutableCollectionTest;

/**
 *
 * @author Ollie
 */
public interface MutableListTest<T> extends ListTest<T>, MutableCollectionTest<T> {

    @Override
    MutableList<T> create(java.util.List<T> elements);

}
