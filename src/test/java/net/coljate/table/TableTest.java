package net.coljate.table;

import net.coljate.collection.CollectionTest;

/**
 *
 * @author ollie
 */
public interface TableTest<R, C, V> extends CollectionTest<Cell<R, C, V>> {

    @Override
    Table<R, C, V> createTestCollection();

}
