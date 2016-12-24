package net.coljate.table;

import net.coljate.set.SetTest;

/**
 *
 * @author ollie
 */
public interface TableTest<R, C, V> extends SetTest<Cell<R, C, V>> {

    @Override
    Table<R, C, V> createTestCollection();
    
}
