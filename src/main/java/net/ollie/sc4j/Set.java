package net.ollie.sc4j;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.imposed.Unique;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.UnmodifiableIterator;

import javax.annotation.CheckReturnValue;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * An iterable collection of unique elements.
 *
 * For example, the integers {@code [1,2,3]}.
 *
 * @author Ollie
 */
public interface Set<V>
        extends Iteratable<V>, Unique<V> {

    @Override
    Set<V> filter(Predicate<? super V> predicate);

    @Override
    <V2> Iteratable<V2> map(Function<? super V, ? extends V2> function);

    @Override
    Set<V> tail();

    @Override
    Set.Mutable<V> mutableCopy();

    @Override
    Set.Immutable<V> immutableCopy();

    @OverridingMethodsMustInvokeSuper
    default boolean equals(final Set<?> that) {
        return that != null
                && this.count() == that.count()
                && this.containsAll(that);
    }

    @Override
    default int hash() {
        return Iterables.sumHashCode(this);
    }

    interface Mutable<V>
            extends Set<V>, Iteratable.Mutable<V> {

        boolean add(V value);

        boolean remove(Object value);

        default boolean addAll(final Iterable<? extends V> iterable) {
            return this.addAll(iterable, V -> true);
        }

        default boolean addAll(final Iterable<? extends V> iterable, final Predicate<? super V> predicate) {
            boolean added = false;
            for (final V value : iterable) {
                if (predicate.test(value)) {
                    added |= this.add(value);
                }
            }
            return added;
        }

        default boolean removeAll(final Iterable<?> iterable) {
            return this.removeAll(iterable, Object -> true);
        }

        default <T> boolean removeAll(final Iterable<T> iterable, final Predicate<? super T> predicate) {
            boolean removed = false;
            for (final T value : iterable) {
                if (predicate.test(value)) {
                    removed |= this.remove(value);
                }
            }
            return removed;
        }

    }

    interface Immutable<V>
            extends Set<V>, Iteratable.Immutable<V> {

        @CheckReturnValue
        Set.Immutable<V> with(V value);

        @CheckReturnValue
        Set.Immutable<V> without(Object value);

        @Override
        Set.Immutable<V> tail();

        @Override
        Set.Immutable<V> filter(Predicate<? super V> predicate);

        @Override
        <V2> Set.Immutable<V2> map(Function<? super V, ? extends V2> function);

        @Override
        UnmodifiableIterator<V> iterator();

        @Override
        default Set.Immutable<V> immutableCopy() {
            return this;
        }

    }

    interface Empty<V>
            extends Set.Immutable<V>, Iteratable.Empty<V> {

        @Override
        public default UnmodifiableIterator<V> iterator() {
            return Iteratable.Empty.super.iterator();
        }

        @Override
        default <V2> Set.Empty<V2> map(final Function<? super V, ? extends V2> function) {
            return (Set.Empty<V2>) this;
        }

        @Override
        default Set.Empty<V> tail() {
            return this;
        }

        @Override
        default Set.Empty<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

    }

}
