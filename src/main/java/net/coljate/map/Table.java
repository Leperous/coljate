package net.coljate.map;

import net.coljate.set.Set;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 */
public interface Table<R, C, V> extends Set<Cell<R, C, V>> {

    boolean contains(Object row, Object column);

    default Cell<R, C, V> cell(final R row, final C column) {
        return this.cellIfPresent(row, column);
    }

    default V get(final R row, final C columnKey) {
        final Cell<R, C, V> cell = this.cell(row, columnKey);
        return cell == null ? null : cell.value();
    }

    @SuppressWarnings("unchecked")
    Cell<R, C, V> cellIfPresent(Object row, Object column);

    default V getIfPresent(final Object row, final Object columnKey) {
        return Functions.ifNonNull(this.cellIfPresent(row, columnKey), Cell::value);
    }

}
