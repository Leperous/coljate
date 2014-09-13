package net.ollie.coljate;

import java.util.Objects;
import java.util.function.Predicate;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.imposed.Distinctness.Unique;
import net.ollie.coljate.utils.iterators.Iterables;
import net.ollie.coljate.utils.iterators.Streams;

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
        extends Streamable<V>, Unique<V> {

    @Override
    Set<V> tail();

    @Override
    Stream<V, ? extends Set<V>> stream();

    @Override
    Set.Mutable<V> mutableCopy();

    @Override
    Set.Immutable<V> immutableCopy();

    @OverridingMethodsMustInvokeSuper
    default boolean equals(final Set<?> that) {
        return that != null
                && Objects.equals(this.count(), that.count())
                && this.containsAll(that);
    }

    @Override
    default int hash() {
        return Iterables.sumHashCode(this);
    }

    @javax.annotation.concurrent.NotThreadSafe
    interface Mutable<V>
            extends Set<V>, Streamable.Mutable<V> {

        boolean add(V value);

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

        @Nonnull
        default Set.Mutable<V> and(final V value) {
            this.add(value);
            return this;
        }

        @Nonnull
        default Set.Mutable<V> not(final Object object) {
            this.remove(object);
            return this;
        }

    }

    @javax.annotation.concurrent.ThreadSafe
    interface Concurrent<V>
            extends Set.Mutable<V> {

    }

    @javax.annotation.concurrent.Immutable
    interface Immutable<V>
            extends Set<V>, Streamable.Immutable<V> {

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
        Set.Immutable<V> not(Object value);

        @Override
        default Set.Immutable<V> tail() {
            return this.not(this.head());
        }

        @Override
        default Set.Immutable<V> immutableCopy() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Empty<V>
            extends Set.Immutable<V>, Streamable.Empty<V> {

        @Override
        default Stream<V, ? extends Set.Empty<V>> stream() {
            return Streams.empty(this);
        }

        @Override
        Set.Singleton<V> and(V value);

        @Override
        default Set.Empty<V> not(final Object value) {
            return this;
        }

        @Override
        default Set.Empty<V> tail() {
            return this;
        }

        @Override
        default Set.Empty<V> immutableCopy() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Singleton<V>
            extends Set.Immutable<V>, Streamable.Singleton<V> {

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

    abstract class Abstract<V> implements Set<V> {

        @Override
        public boolean equals(final Object object) {
            return object instanceof Set && this.equals((Set<?>) object);
        }

        @Override
        public int hashCode() {
            return this.hash();
        }

        @Override
        public String toString() {
            return Iterables.safelyToString(this, this);
        }

    }

}
