package net.coljate.table;

import java.util.Objects;

import net.coljate.collection.MutableCollection;

/**
 *
 * @author ollie
 */
public interface MutableTable<R, C, V>
        extends Table<R, C, V>, MutableCollection<Cell<R, C, V>> {

    V put(R rowKey, C columnKey, V value);

    default boolean add(final Cell<R, C, V> cell) {
        return this.addCell(cell);
    }

    default boolean addCell(final Cell<? extends R, ? extends C, ? extends V> cell) {
        return this.add(cell.rowKey(), cell.columnKey(), cell.value());
    }

    default boolean add(final R rowKey, final C columnKey, final V value) {
        return Objects.equals(this.get(rowKey, columnKey), value) && this.contains(rowKey, columnKey)
                ? false
                : this.put(rowKey, columnKey, value) == null;
    }

    boolean remove(Object rowKey, Object columnKey, Object value);

    default boolean remove(final Cell<?, ?, ?> cell) {
        return this.remove(cell.rowKey(), cell.columnKey(), cell.value());
    }

    default boolean remove(final Object rowKey, final Object columnKey) {
        final Cell<R, C, V> cell = this.cellIfPresent(rowKey, columnKey);
        return this.remove(cell);
    }

    static <R, C, V> MutableTable<R, C, V> copyOf(final Table<? extends R, ? extends C, ? extends V> table) {
        throw new UnsupportedOperationException(); //TODO
    }

}
