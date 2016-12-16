package net.coljate.map;

import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public interface Table<R, C, V> extends Set<Cell<R, C, V>> {

    boolean contains(Object row, Object column);

    default Cell<R, C, V> cell(final R row, final C column) {
        return this.cellIfPresent(row, column);
    }

    @SuppressWarnings("unchecked")
    Cell<R, C, V> cellIfPresent(Object row, Object column);

}
