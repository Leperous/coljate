package net.coljate.table.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableMap;
import net.coljate.table.Cell;
import net.coljate.table.ImmutableCell;
import net.coljate.table.ImmutableTable;

/**
 *
 * @author ollie
 */
public class ImmutableMapBackedTable<R, C, V>
        extends MapBackedTable<R, C, V>
        implements ImmutableTable<R, C, V> {

    protected ImmutableMapBackedTable(final ImmutableMap<KeyPair<R, C>, V> map) {
        super(map);
    }

    @Override
    public ImmutableCell<R, C, V> cellIfPresent(final Object row, final Object column) {
        return this.toCell(this.entry(row, column));
    }

    @Override
    protected ImmutableCell<R, C, V> toCell(final Entry<KeyPair<R, C>, V> entry) {
        return entry == null
                ? null
                : new ImmutableCell<>(entry.key().rowKey(), entry.key().columnKey(), entry.value());
    }

    @Override
    public UnmodifiableIterator<Cell<R, C, V>> iterator() {
        return UnmodifiableIterator.wrap(super.iterator());
    }

}
