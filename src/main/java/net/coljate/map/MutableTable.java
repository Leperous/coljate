package net.coljate.map;

import java.util.Objects;

import net.coljate.set.MutableSet;

/**
 *
 * @author ollie
 */
public interface MutableTable<R, C, V>
        extends Table<R, C, V>, MutableSet<Cell<R, C, V>> {

    V put(R rowKey, C columnKey, V value);

    @Override
    default boolean add(final Cell<R, C, V> cell) {
        return this.add(cell.rowKey(), cell.columnKey(), cell.value());
    }

    default boolean add(final R rowKey, final C columnKey, final V value) {
        return Objects.equals(this.get(rowKey, columnKey), value)
                ? false
                : this.put(rowKey, columnKey, value) == null;
    }

    boolean remove(Object rowKey, Object columnKey, Object value);

    @Override
    @Deprecated
    default boolean remove(final Object object) {
        return object instanceof Cell
                && this.remove((Cell) object);
    }

    default boolean remove(final Cell<?, ?, ?> cell) {
        return this.remove(cell.rowKey(), cell.columnKey(), cell.value());
    }

    default boolean remove(final Object rowKey, final Object columnKey) {
        final Cell<R, C, V> cell = this.cellIfPresent(rowKey, columnKey);
        return this.remove(cell);
    }

}
