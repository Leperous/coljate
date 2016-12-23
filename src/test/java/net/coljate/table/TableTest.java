package net.coljate.table;

import net.coljate.set.SetTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public interface TableTest<R, C, V> extends SetTest<Cell<R, C, V>> {

    @Override
    Table<R, C, V> create(java.util.List<Cell<R, C, V>> cells);

}
