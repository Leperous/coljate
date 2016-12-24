package net.coljate.table;

import net.coljate.collection.MutableCollectionTest;

/**
 *
 * @author ollie
 */
public interface MutableTableTest<R, C, V> extends TableTest<R, C, V>, MutableCollectionTest<Cell<R, C, V>> {

    @Override
    MutableTable<R, C, V> createTestCollection();

}
