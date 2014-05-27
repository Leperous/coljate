package net.ollie.sc4j;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.Iterables.UnmodifiableIterator;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * An iterable, sorted collection of objects.
 *
 * @author Ollie
 * @see Array for index-accessible form.
 * @see Stream for a (potentially) infinitely long list.
 */
public interface List<V>
        extends Sequence<V>, Iteratable<V> {

    @Override
    default V head() {
        return Sequence.super.head();
    }

    @Override
    List<V> tail();

    @Override
    <V2> List<V2> map(Function<? super V, ? extends V2> function);

    @Override
    List<V> filter(Predicate<? super V> predicate);

    @CheckReturnValue
    @Nonnull
    Array<V> toArray();

    @Override
    List.Mutable<V> mutable();

    @Override
    List.Immutable<V> immutable();

    @OverridingMethodsMustInvokeSuper
    default boolean equals(final List<?> that) {
        return that != null
                && this.size() == that.size()
                && Iterables.equals(this, that);
    }

    /**
     * A mutable list.
     *
     * @param <V>
     */
    interface Mutable<V>
            extends List<V>, Sequence.Mutable<V> {

        void prefix(V value);

        void prefixAll(Iterable<? extends V> values);

        void suffix(V value);

        void suffixAll(Iterable<? extends V> values);

        default boolean removeFirst(final Object object) {
            final Iterator<V> iterator = this.iterator();
            while (iterator.hasNext()) {
                if (Objects.equals(iterator.next(), object)) {
                    iterator.remove();
                    return true;
                }
            }
            return false;
        }

        default int removeAll(Object object) {
            int removed = 0;
            for (final Iterator<V> iterator = this.iterator(); iterator.hasNext();) {
                final V next = iterator.next();
                if (Objects.equals(next, object)) {
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
            extends List<V>, Sequence.Immutable<V> {

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
        Array.Immutable<V> toArray();

        @Override
        default List.Immutable<V> immutable() {
            return this;
        }

        @Override
        UnmodifiableIterator<V> iterator();

    }

    interface Empty<V>
            extends Sequence.Empty<V>, List.Immutable<V> {

        @Override
        default boolean isEmpty() {
            return Sequence.Empty.super.isEmpty();
        }

        @Override
        default boolean contains(Object object) {
            return Sequence.Empty.super.contains(object);
        }

        @Override
        default List.Empty<V> tail() {
            return this;
        }

        @Override
        default List.Empty<V> withoutFirst(final Object value) {
            return this;
        }

        @Override
        default List.Empty<V> withoutAll(final Object value) {
            return this;
        }

        @Override
        default List.Empty<V> filter(Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default List.Empty<V> immutable() {
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        default <V2> List.Empty<V2> map(Function<? super V, ? extends V2> function) {
            return (List.Empty<V2>) this;
        }

        @Override
        default V first() {
            return null;
        }

        @Override
        default V last() {
            return null;
        }

        @Override
        List.Empty<V> sort(Comparator<? super V> comparator);

        @Override
        public default V findOrElse(Predicate<? super V> predicate, V defaultValue) {
            return Sequence.Empty.super.findOrElse(predicate, defaultValue);
        }

    }

}
