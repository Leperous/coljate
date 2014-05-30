package net.ollie.sc4j;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Keyed.BiKeyed;
import net.ollie.sc4j.imposed.Cached;

/**
 * A cache of values that are {@link #get accessed} by providing a row and column.
 *
 * @author Ollie
 * @param <R> row type
 * @param <C> column type
 * @param <V> value type
 */
public interface Table<R, C, V>
        extends BiKeyed<R, C, V>, Cached<java.util.Map.Entry<R, C>, V> {

    Array<R> rows();

    Map<C, V> row(R row);

    Array<C> columns();

    Map<R, V> column(C column);

    @Override
    <V2> Table<R, C, V2> mapValues(Function<? super V, ? extends V2> function);

    @Override
    Table<R, C, V> filterValues(Predicate<? super V> predicate);

    @Override
    Table.Mutable<R, C, V> mutable();

    @Override
    Table.Immutable<R, C, V> immutable();

    interface Mutable<R, C, V>
            extends Table<R, C, V>, Cached.Mutable<java.util.Map.Entry<R, C>, V> {

        V put(R row, C column, V value);

        @Override
        default V put(final java.util.Map.Entry<R, C> location, final V value) {
            return this.put(location.getKey(), location.getValue(), value);
        }

    }

    interface Immutable<R, C, V>
            extends Table<R, C, V>, Cached.Immutable<java.util.Map.Entry<R, C>, V> {

        @Override
        Array.Immutable<R> rows();

        @Override
        Map.Immutable<C, V> row(R row);

        @Override
        Array.Immutable<C> columns();

        @Override
        Map.Immutable<R, V> column(C column);

        @Override
        <V2> Table.Immutable<R, C, V2> mapValues(Function<? super V, ? extends V2> function);

        @Override
        Table.Immutable<R, C, V> filterValues(Predicate<? super V> predicate);

        @Override
        default Table.Immutable<R, C, V> immutable() {
            return this;
        }

    }

}
