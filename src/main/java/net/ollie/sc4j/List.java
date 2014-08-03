package net.ollie.sc4j;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Finite;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.UnmodifiableIterator;

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
        extends Sequence<V>, Finite<V> {

    @Override
    default V head() {
        return Finite.super.head();
    }

    @Override
    List<V> tail();

    @CheckForNull
    V last();

    @Override
    <V2> List<V2> map(Function<? super V, ? extends V2> function);

    @Override
    List<V> filter(Predicate<? super V> predicate);

    @Nonnull
    List<V> reverse();

    @Override
    <R> List<R> flatMap(Function<? super V, ? extends Finite<R>> function);

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
    interface Mutable<V>
            extends List<V>, Sequence.Mutable<V>, Finite.Mutable<V> {

        void prefix(V value);

        void prefixAll(Iterable<? extends V> values);

        void suffix(V value);

        @Override
        List.Mutable<V> reverse();

        default void suffixAll(final Iterable<? extends V> values) {
            for (final V value : values) {
                this.suffix(value);
            }
        }

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

        default int removeAll(final Object value) {
            int removed = 0;
            for (final Iterator<V> iterator = this.iterator(); iterator.hasNext();) {
                final V next = iterator.next();
                if (Objects.equals(next, value)) {
                    iterator.remove();
                    removed++;
                }
            }
            return removed;
        }

    }

    /**
     * An immutable list.
     *
     * @param <V>
     */
    interface Immutable<V>
            extends List<V>, Sequence.Immutable<V>, Finite.Immutable<V> {

        @Override
        List.Immutable<V> tail();

        @CheckReturnValue
        List.Immutable<V> withPrefix(V value);

        @CheckReturnValue
        List.Immutable<V> withSuffix(V value);

        @CheckReturnValue
        List.Immutable<V> withoutFirst(Object value);

        @CheckReturnValue
        List.Immutable<V> withoutAll(Object value);

        @Override
        <V2> List.Immutable<V2> map(Function<? super V, ? extends V2> function);

        @Override
        List.Immutable<V> filter(Predicate<? super V> predicate);

        @Override
        <R> List.Immutable<R> flatMap(Function<? super V, ? extends Finite<R>> function);

        @Override
        List.Immutable<V> reverse();

        @Override
        default List.Immutable<V> immutableCopy() {
            return this;
        }

        @Override
        UnmodifiableIterator<V> iterator();

    }

    interface Empty<V>
            extends List.Immutable<V>, Sequence.Empty<V>, Finite.Empty<V> {

        @Override
        default V head() {
            return Sequence.Empty.super.head();
        }

        @Override
        default UnmodifiableIterator<V> iterator() {
            return Finite.Empty.super.iterator();
        }

        @Override
        default <V2> List.Empty<V2> map(Function<? super V, ? extends V2> function) {
            return (List.Empty<V2>) this;
        }

        @Override
        default List.Empty<V> tail() {
            return this;
        }

        @Override
        default List.Empty<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default List.Empty<V> immutableCopy() {
            return this;
        }

    }

    interface Singleton<V>
            extends List<V>, Sequence.Singleton<V>, Finite.Singleton<V> {

        @Override
        default V head() {
            return Finite.Singleton.super.head();
        }

        @Override
        default V last() {
            return this.value();
        }

        @Override
        List.Empty<V> tail();

        @Override
        default UnmodifiableIterator<V> iterator() {
            return Finite.Singleton.super.iterator();
        }

    }

}
