package net.ollie.sc4j;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.imposed.Cached;
import net.ollie.sc4j.utils.Functions;

import javax.annotation.Nonnull;

/**
 * A cache of values that are {@link #get accessed} by providing a row and column.
 *
 * @author Ollie
 * @param <R> row type, generally an {@link java.lang.Integer integer}.
 * @param <C> column type, generally an {@link java.lang.Enum enumeration}.
 * @param <V> value type
 */
public interface Table<R, C, V>
        extends Keyed.Dual<R, C, V>, Cached<Map.Entry<R, C>, V> {

    @Override
    V get(Object row, Object column);

    @Nonnull
    Array<R> rows();

    @Nonnull
    Map<C, V> row(R row);

    @Nonnull
    Array<C> columns();

    @Nonnull
    Map<R, V> column(C column);

    @Override
    <V2> Table<R, C, V2> mapValues(Function<? super V, ? extends V2> function);

    @Override
    default Table<R, C, V> filter(Predicate<? super V> predicate) {
        return this.filterValues(predicate);
    }

    @Override
    Table<R, C, V> filterValues(Predicate<? super V> predicate);

    @Override
    Table.Mutable<R, C, V> mutableCopy();

    @Override
    Table.Immutable<R, C, V> immutableCopy();

    interface Mutable<R, C, V>
            extends Table<R, C, V>, Cached.Mutable<Map.Entry<R, C>, V> {

        V put(R row, C column, V value);

        V remove(Object row, Object column);

        @Override
        default V remove(final Object object) {
            if (object instanceof Map.Entry) {
                final Map.Entry<?, ?> location = (Map.Entry) object;
                return this.remove(location.key(), location.value());
            } else {
                return null;
            }
        }

        @Override
        default V put(final Map.Entry<R, C> location, final V value) {
            return this.put(location.key(), location.value(), value);
        }

    }

    interface Immutable<R, C, V>
            extends Table<R, C, V>, Cached.Immutable<Map.Entry<R, C>, V> {

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
        default Table.Immutable<R, C, V> filter(Predicate<? super V> predicate) {
            return this.filterValues(predicate);
        }

        @Override
        default Table.Immutable<R, C, V> filterValues(final Predicate<? super V> predicate) {
            return this.mapValues(Functions.satisfying(predicate));
        }

        @Override
        default Table.Immutable<R, C, V> immutableCopy() {
            return this;
        }

    }

}
