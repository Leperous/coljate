package net.ollie.coljate.maps;


import net.ollie.coljate.Array;
import net.ollie.coljate.Map;
import net.ollie.coljate.Set;
import net.ollie.coljate.Table;
import net.ollie.coljate.access.Streamable;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedTable<R, C, V>
        implements Table.Immutable<R, C, V> {

    public static <R, C, V> Table.Immutable<R, C, V> copy(final Table<R, C, V> table) {
        return new ImmutableWrappedTable<>(MutableWrappedTable.copy(table));
    }

    private final Table<R, C, V> delegate;

    protected ImmutableWrappedTable(final Table<R, C, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public V get(final Object key1, final Object key2) {
        return delegate.get(key1, key2);
    }

    @Override
    public V get(final Object key) {
        return delegate.get(key);
    }

    @Override
    public Array.Immutable<R> rows() {
        return delegate.rows().immutableCopy();
    }

    @Override
    public Map.Immutable<C, V> row(final R row) {
        return delegate.row(row).immutableCopy();
    }

    @Override
    public Array.Immutable<C> columns() {
        return delegate.columns().immutableCopy();
    }

    @Override
    public Map.Immutable<R, V> column(final C column) {
        return delegate.column(column).immutableCopy();
    }

    @Override
    public Set<Map.Entry<R, C>> keys() {
        throw new UnsupportedOperationException("keys not supported yet!");
    }

    @Override
    public Streamable.Immutable<V> values() {
        throw new UnsupportedOperationException("values not supported yet!");
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public Table.Mutable<R, C, V> mutableCopy() {
        return delegate.mutableCopy();
    }

    @Override
    public String toString(final String separator) {
        throw new UnsupportedOperationException("toString not supported yet!");
    }

}
