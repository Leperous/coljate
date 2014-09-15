package net.ollie.coljate.maps;

import net.ollie.coljate.lists.Array;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.imposed.Distinctness.Unique;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class MutableWrappedTable<R, C, V>
        implements Table.Mutable<R, C, V> {

    public static <R, C, V> Table.Mutable<R, C, V> empty() {
        return new MutableWrappedTable<>(MutableWrappedHashMap.create());
    }

    public static <R, C, V> Table.Mutable<R, C, V> view(final Map.Mutable<R, Map.Mutable<C, V>> map) {
        return new MutableWrappedTable<>(map);
    }

    public static <R, C, V> Table.Mutable<R, C, V> copy(final Table<R, C, V> table) {
        Map.Mutable<R, Map.Mutable<C, V>> map = MutableWrappedHashMap.create(table.rows().count());
        for (final R row : table.rows()) {
            map.put(row, table.row(row).mutableCopy());
        }
        return view(map);
    }

    private final Map.Mutable<R, Map.Mutable<C, V>> values;

    protected MutableWrappedTable(final Map.Mutable<R, Map.Mutable<C, V>> values) {
        this.values = values;
    }

    @Override
    public V get(final Object row, final Object column) {
        final Map<C, V> map = values.get(row);
        return map == null
                ? null
                : map.get(column);
    }

    @Override
    public V put(final R row, final C column, final V value) {
        return this.ensure(row).put(column, value);
    }

    @Nonnull
    protected Map.Mutable<C, V> ensure(final R row) {
        Map.Mutable<C, V> actualRow = values.get(row);
        if (actualRow == null) {
            actualRow = this.createRow();
            values.put(row, actualRow);
        }
        return actualRow;
    }

    protected Map.Mutable<C, V> createRow() {
        return MutableWrappedHashMap.create();
    }

    @Override
    public V remove(final Object row, final Object column) {
        final Map.Mutable<C, V> actualRow = values.get(row);
        return actualRow == null
                ? null
                : actualRow.remove(column);
    }

    protected <A, B> Map.Entry<A, B> createEntry(final A key, final B value) {
        return new Entries.MutableEntry<>(key, value);
    }

    @Override
    public Array<R> rows() {
        throw new UnsupportedOperationException("rows not supported yet!");
    }

    @Override
    public Map<C, V> row(final R rowKey) {
        final Map<C, V> actualRow = values.get(rowKey);
        return actualRow == null
                ? MutableWrappedHashMap.create()
                : actualRow;
    }

    @Override
    public Array<C> columns() {
        throw new UnsupportedOperationException("columns not supported yet!"); //TODO columns
    }

    @Override
    public Map<R, V> column(final C column) {
        throw new UnsupportedOperationException("column not supported yet!");
    }

    @Override
    public Table.Mutable<R, C, V> mutableCopy() {
        return MutableWrappedTable.copy(this);
    }

    @Override
    public Table.Immutable<R, C, V> immutableCopy() {
        return ImmutableWrappedTable.copy(this);
    }

    @Override
    public Unique<Map.Entry<R, C>> keys() {
        throw new UnsupportedOperationException("keys not supported yet!");
    }

    @Override
    public Streamable<V> values() {
        throw new UnsupportedOperationException("values not supported yet!");
    }

    @Override
    public V putIfAbsent(Map.Entry<R, C> key, V value) {
        throw new UnsupportedOperationException("putIfAbsent not supported yet!");
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException("remove not supported yet!");
    }

    @Override
    public boolean replace(Map.Entry<R, C> key, V oldValue, V newValue) {
        throw new UnsupportedOperationException("replace not supported yet!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("clear not supported yet!");
    }

    @Override
    public String toString(final String separator) {
        throw new UnsupportedOperationException("toString not supported yet!");
    }

}
