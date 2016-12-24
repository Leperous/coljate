package net.coljate.table;

import net.coljate.collection.Collection;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 */
public interface Table<R, C, V> extends Collection<Cell<R, C, V>> {

    @SuppressWarnings("unchecked")
    Cell<R, C, V> cellIfPresent(Object row, Object column);

    default boolean contains(final Object row, final Object column) {
        return this.cellIfPresent(row, column) != null;
    }

    default Cell<R, C, V> cell(final R row, final C column) {
        return this.cellIfPresent(row, column);
    }

    default V get(final R row, final C columnKey) {
        final Cell<R, C, V> cell = this.cell(row, columnKey);
        return cell == null ? null : cell.value();
    }

    default V getIfPresent(final Object row, final Object columnKey) {
        return Functions.ifNonNull(this.cellIfPresent(row, columnKey), Cell::value);
    }

    @Override
    default MutableTable<R, C, V> mutableCopy() {
        return MutableTable.copyOf(this);
    }

    @Override
    ImmutableTable<R, C, V> immutableCopy();

}
