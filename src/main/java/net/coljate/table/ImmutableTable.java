package net.coljate.table;

import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public interface ImmutableTable<R, C, V>
        extends Table<R, C, V>, ImmutableSet<Cell<R, C, V>> {

    @Override
    ImmutableCell<R, C, V> cellIfPresent(Object row, Object column);

    @Override
    default ImmutableCell<R, C, V> cell(final R row, final C column) {
        return this.cellIfPresent(row, column);
    }

    @Override
    @Deprecated
    default ImmutableTable<R, C, V> immutableCopy() {
        return this;
    }

}
