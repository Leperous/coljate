package net.ollie.coljate.lists;

import java.util.Iterator;
import java.util.Objects;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.utils.iterators.Iterables;
import net.ollie.coljate.utils.iterators.Streams;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * An iterable sequence.
 *
 * @author Ollie
 * @see Array for index-accessible form.
 */
public interface List<V>
        extends Sequence<V>, Streamable<V> {

    @Override
    default V head() {
        return Streamable.super.head();
    }

    @Override
    List<V> tail();

    @Override
    Stream<V, ? extends List<V>> stream();

    @CheckForNull
    V last();

    @Nonnull
    List<V> reverse();

    @Override
    List.Mutable<V> mutableCopy();

    @Override
    List.Immutable<V> immutableCopy();

    @OverridingMethodsMustInvokeSuper
    default boolean equals(final List<?> that) {
        return that != null
                && Iterables.equals(this, that);
    }

    @Override
    default int hash() {
        return Iterables.productHashCode(this);
    }

    /**
     * A mutable list.
     *
     * @param <V>
     */
    @javax.annotation.concurrent.NotThreadSafe
    interface Mutable<V>
            extends List<V>, Sequence.Mutable<V>, Streamable.Mutable<V> {

        void prefix(V value);

        void prefixAll(Iterable<? extends V> values);

        void suffix(V value);

        default void suffixAll(@Nonnull final Iterable<? extends V> values) {
            for (final V value : values) {
                this.suffix(value);
            }
        }

        @Override
        List.Mutable<V> reverse();

        default boolean removeFirst(final Object value) {
            final Iterator<V> iterator = this.iterator();
            while (iterator.hasNext()) {
                if (Objects.equals(iterator.next(), value)) {
                    iterator.remove();
                    return true;
                }
            }
            return false;
        }

        @Nonnull
        default NonNegativeInteger removeEvery(final Object value) {
            int removed = 0;
            for (final Iterator<V> iterator = this.iterator(); iterator.hasNext();) {
                final V next = iterator.next();
                if (Objects.equals(next, value)) {
                    iterator.remove();
                    removed++;
                }
            }
            return NonNegativeInteger.of(removed);
        }

        @Nonnull
        default NonNegativeInteger removeAll(final Iterable<? extends V> values) {
            NonNegativeInteger i = NonNegativeInteger.ZERO;
            for (final V value : values) {
                i = i.plus(this.removeEvery(value));
            }
            return i;
        }

        @Nonnull
        default Inliner<V, ? extends List.Mutable<V>> inline() {
            return new Inliner<>(this);
        }

        class Inliner<V, L extends List.Mutable<V>> {

            private final L list;

            protected Inliner(final L underlying) {
                this.list = underlying;
            }

            @Nonnull
            public Inliner<V, L> prefix(final V value) {
                list.prefix(value);
                return this;
            }

            @Nonnull
            public Inliner<V, L> prefixAll(final Iterable<? extends V> values) {
                list.prefixAll(values);
                return this;
            }

            @Nonnull
            public Inliner<V, L> suffix(final V value) {
                list.suffix(value);
                return this;
            }

            @Nonnull
            public Inliner<V, L> suffixAll(final Iterable<? extends V> values) {
                list.suffixAll(values);
                return this;
            }

            @Nonnull
            public Inliner<V, L> removeFirst(final Object object) {
                list.removeFirst(object);
                return this;
            }

            @Nonnull
            public Inliner<V, L> removeEvery(final Object object) {
                list.removeEvery(object);
                return this;
            }

            @Nonnull
            public Inliner<V, L> removeAll(final Iterable<? extends V> values) {
                list.removeAll(values);
                return this;
            }

            public L list() {
                return list;
            }

        }

    }

    @javax.annotation.concurrent.ThreadSafe
    interface Concurrent<V>
            extends List.Mutable<V> {

    }

    /**
     * An immutable list.
     *
     * @param <V>
     */
    @javax.annotation.concurrent.Immutable
    interface Immutable<V>
            extends List<V>, Sequence.Immutable<V>, Streamable.Immutable<V> {

        @Override
        List.Immutable<V> tail();

        @CheckReturnValue
        List.Immutable<V> andPrefix(V value);

        @CheckReturnValue
        List.Immutable<V> andSuffix(V value);

        @CheckReturnValue
        List.Immutable<V> notFirst(Object value);

        @CheckReturnValue
        List.Immutable<V> notAll(Object value);

        @Override
        List.Immutable<V> reverse();

        @Override
        default List.Immutable<V> immutableCopy() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Empty<V>
            extends List.Immutable<V>, Sequence.Empty<V>, Streamable.Empty<V> {

        @Override
        default V head() {
            return Sequence.Empty.super.head();
        }

        @Override
        List.Singleton<V> andPrefix(V value);

        @Override
        default List.Singleton<V> andSuffix(final V value) {
            return this.andPrefix(value);
        }

        @Override
        default List.Empty<V> notFirst(final Object value) {
            return this;
        }

        @Override
        default List.Empty<V> notAll(final Object value) {
            return this;
        }

        @Override
        default UnmodifiableIterator<V> iterator() {
            return Streamable.Empty.super.iterator();
        }

        @Override
        default List.Empty<V> tail() {
            return this;
        }

        @Override
        default V last() {
            return null;
        }

        @Override
        default List.Empty<V> reverse() {
            return this;
        }

        @Override
        public default Stream<V, ? extends List.Empty<V>> stream() {
            return Streams.empty(this);
        }

        @Override
        default List.Empty<V> immutableCopy() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Singleton<V>
            extends List.Immutable<V>, Sequence.Singleton<V>, Streamable.Singleton<V> {

        @Override
        default V first() {
            return this.value();
        }

        @Override
        default V head() {
            return this.first();
        }

        @Override
        default V last() {
            return this.value();
        }

        @Override
        List.Empty<V> tail();

        @Override
        default List.Immutable<V> notAll(final Object value) {
            return this.notFirst(value);
        }

        @Override
        default List.Singleton<V> reverse() {
            return this;
        }

    }

    abstract class Abstract<V> extends Streamable.Abstract<V> implements List<V> {

        @Override
        public boolean equals(final Object object) {
            return object instanceof List && this.equals((List) object);
        }

        @Override
        public int hashCode() {
            return this.hash();
        }

    }

}
