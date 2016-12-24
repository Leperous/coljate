package net.coljate.table.lazy;

import net.coljate.collection.lazy.LazyCollection;
import net.coljate.table.Cell;
import net.coljate.table.ImmutableTable;
import net.coljate.table.MutableTable;
import net.coljate.table.Table;

/**
 *
 * @author ollie
 */
public interface LazyTable<R, C, V> extends Table<R, C, V>, LazyCollection<Cell<R, C, V>> {

    @Override
    MutableTable<R, C, V> mutableCopy();

    @Override
    ImmutableTable<R, C, V> immutableCopy();

}
