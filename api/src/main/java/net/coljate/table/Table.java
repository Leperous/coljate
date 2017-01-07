package net.coljate.table;

import javax.annotation.CheckForNull;

import net.coljate.set.Set;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 */
public interface Table<R, C, V> extends Set<Cell<R, C, V>> {

    @SuppressWarnings("unchecked")
    @CheckForNull
    Cell<R, C, V> cellIfPresent(Object rowKey, Object columnKey);

    default boolean contains(final Object rowKey, final Object columnKey) {
        return this.cellIfPresent(rowKey, columnKey) != null;
    }

    default Cell<R, C, V> cell(final R rowKey, final C columnKey) {
        return this.cellIfPresent(rowKey, columnKey);
    }

    default V get(final R rowKey, final C columnKey) {
        final Cell<R, C, V> cell = this.cell(rowKey, columnKey);
        return cell == null ? null : cell.value();
    }

    default V getIfPresent(final Object rowKey, final Object columnKey) {
        return Functions.ifNonNull(this.cellIfPresent(rowKey, columnKey), Cell::value);
    }

    default void forEach(final CellConsumer<? super R, ? super C, ? super V> consumer) {
        this.forEach(cell -> consumer.accept(cell));
    }

    @Override
    default MutableTable<R, C, V> mutableCopy() {
        return MutableTable.copyOf(this);
    }

    @Override
    default ImmutableTable<R, C, V> immutableCopy() {
        return ImmutableTable.copyOf(this);
    }

}
