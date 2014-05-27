package net.ollie.sc4j;

import net.ollie.sc4j.access.Keyed.BiKeyed;
import net.ollie.sc4j.imposed.Cached;

/**
 * A cache of values that are {@link #get accessed} by providing a row and
 * column.
 *
 * @author Ollie
 * @param <R> row type
 * @param <C> column type
 * @param <V> value type
 */
public interface Table<R, C, V>
        extends BiKeyed<R, C, V>, Cached<java.util.Map.Entry<R, C>, V> {

    Set<R> rows();

    Map<C, V> row(R row);

    Set<C> columns();

    Map<R, V> column(C column);

    @Override
    Table.Mutable<R, C, V> mutable();

    @Override
    Table.Immutable<R, C, V> immutable();

    interface Mutable<R, C, V>
            extends Table<R, C, V>, Cached.Mutable<java.util.Map.Entry<R, C>, V> {

        V put(R rowKey, C columnKey, V value);

        @Override
        default V put(java.util.Map.Entry<R, C> key, V value) {
            return this.put(key.getKey(), key.getValue(), value);
        }

    }

    interface Immutable<R, C, V>
            extends Table<R, C, V>, Cached.Immutable<java.util.Map.Entry<R, C>, V> {

        @Override
        default Table.Immutable<R, C, V> immutable() {
            return this;
        }

    }

}
