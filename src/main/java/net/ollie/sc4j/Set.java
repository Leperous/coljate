package net.ollie.sc4j;

import java.util.function.Consumer;
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
 * A finite collection create unique elements.
 * <p/>
 * For example, the integers {@code [1, 2, 3]}.
 *
 * @author Ollie
 */
public interface Set<V>
        extends Finite<V>, Unique<V> {

//    @Override
//    Stream<V, ? extends Set<V>> stream();

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

    @javax.annotation.concurrent.NotThreadSafe
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

        default Inliner<V, ? extends Set.Mutable<V>> inline() {
            return new Inliner<>(this);
        }

        class Inliner<V, S extends Set.Mutable<V>> {

            private final S set;

            protected Inliner(final S set) {
                this.set = set;
            }

            public Inliner<V, S> add(final V value) {
                set.add(value);
                return this;
            }

            public Inliner<V, S> addAll(final Iterable<? extends V> iterable) {
                set.addAll(iterable);
                return this;
            }

            public Inliner<V, S> remove(final Object value) {
                set.remove(value);
                return this;
            }

            public Inliner<V, S> op(final Consumer<? super S> consumer) {
                consumer.accept(set);
                return this;
            }

            public S set() {
                return set;
            }

        }

    }

    @javax.annotation.concurrent.ThreadSafe
    interface Concurrent<V>
            extends Set.Mutable<V> {

    }

    @javax.annotation.concurrent.Immutable
    interface Immutable<V>
            extends Set<V>, Finite.Immutable<V> {

        @CheckReturnValue
        @Nonnull
        Set.Immutable<V> and(@Nonnull V value);

        @CheckReturnValue
        @Nonnull
        default Set.Immutable<V> andAll(@Nonnull final Iterable<? extends V> values) {
            Set.Immutable<V> set = this;
            for (final V value : values) {
                set = set.and(value);
            }
            return set;
        }

        @Nonnull
        default Set.Immutable<V> andMaybe(@CheckForNull final V value) {
            return value == null ? this : this.and(value);
        }

        @CheckReturnValue
        Set.Immutable<V> not(Object element);

        @Override
        Set.Immutable<V> tail();

        @Override
        default Set.Immutable<V> immutableCopy() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Empty<V>
            extends Set.Immutable<V>, Finite.Empty<V> {

        @Override
        Set.Singleton<V> and(V value);

        @Override
        default Set.Empty<V> not(final Object value) {
            return this;
        }

        @Override
        public default UnmodifiableIterator<V> iterator() {
            return Finite.Empty.super.iterator();
        }

        @Override
        default Set.Empty<V> tail() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Singleton<V>
            extends Set.Immutable<V>, Finite.Singleton<V> {

        @Override
        Set.Empty<V> tail();

    }

    @javax.annotation.concurrent.Immutable
    interface Binary<V>
            extends Set.Immutable<V> {

        @CheckForNull
        V left();

        @CheckForNull
        V right();

    }

}
