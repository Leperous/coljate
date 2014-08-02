package net.ollie.sc4j;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Finite;
import net.ollie.sc4j.imposed.Distinctness.Unique;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.UnmodifiableIterator;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * A finite collection of unique elements.
 *
 * For example, the integers {@code [1,2,3]}.
 *
 * @author Ollie
 */
public interface Set<V>
        extends Finite<V>, Unique<V> {

    @Override
    Set<V> filter(Predicate<? super V> predicate);

    @Override
    <V2> Set<V2> map(Function<? super V, ? extends V2> function);

    @Override
    Set<V> tail();

    @Override
    <R> Set<R> flatMap(Function<? super V, ? extends Finite<R>> function);

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
            extends Set<V>, Finite.Mutable<V> {

        boolean add(V value);

        boolean remove(@Nullable Object value);

        default boolean removeWhere(final Predicate<? super V> predicate) {
            boolean removed = false;
            for (final V value : this) {
                if (predicate.test(value)) {
                    removed |= this.remove(value);
                }
            }
            return removed;
        }

        default boolean addAll(final Iterable<? extends V> iterable) {
            return this.addAll(iterable, o -> true);
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
            return this.removeAll(iterable, o -> true);
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
            extends Set<V>, Finite.Immutable<V> {

        @CheckReturnValue
        @Nonnull
        Set.Immutable<V> with(@Nonnull V value);

        @CheckReturnValue
        @Nonnull
        default Set.Immutable<V> withAll(final Iterable<? extends V> values) {
            Set.Immutable<V> set = this;
            for (final V value : values) {
                set = set.with(value);
            }
            return set;
        }

        @Nonnull
        default Set.Immutable<V> maybeWith(@CheckForNull final V value) {
            return value == null ? this : this.with(value);
        }

        @CheckReturnValue
        Set.Immutable<V> without(Object value);

        @Override
        <R> Set.Immutable<R> flatMap(Function<? super V, ? extends Finite<R>> function);

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
            extends Set.Immutable<V>, Finite.Empty<V> {

        @Override
        public default UnmodifiableIterator<V> iterator() {
            return Finite.Empty.super.iterator();
        }

        @Override
        @SuppressWarnings("unchecked")
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

    interface Singleton<V>
            extends Set<V>, Finite.Singleton<V> {

        @Override
        Set.Empty<V> tail();

        @Override
        default UnmodifiableIterator<V> iterator() {
            return Finite.Singleton.super.iterator();
        }

    }

    interface Binary<V>
            extends Set<V> {

        @CheckForNull
        V left();

        @CheckForNull
        V right();

    }

}
