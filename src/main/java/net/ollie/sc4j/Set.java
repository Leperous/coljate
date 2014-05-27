package net.ollie.sc4j;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.access.Traversable;
import net.ollie.sc4j.imposed.Unique;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.Iterables.UnmodifiableIterator;

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
    <V2> Set<V2> map(Function<? super V, ? extends V2> function);

    @Override
    Set<V> tail();

    @Override
    Set.Mutable<V> mutable();

    @Override
    Set.Immutable<V> immutable();

    @OverridingMethodsMustInvokeSuper
    default boolean equals(final Set<?> that) {
        return that != null
                && this.size() == that.size()
                && this.containsAll(that);
    }

    interface Mutable<V>
            extends Set<V>, Traversable.Mutable<V>, Unique.Mutable<V> {

        boolean add(V value);

        boolean addAll(Iterable<? extends V> iterable);

        boolean remove(Object value);

        boolean removeAll(Iterable<?> iterable);

    }

    interface Immutable<V>
            extends Set<V>, Traversable.Immutable<V>, Unique.Immutable<V> {

        @CheckReturnValue
        Set.Immutable<V> with(V value);

        @CheckReturnValue
        Set.Immutable<V> without(Object object);

        @Override
        Set.Immutable<V> tail();

        @Override
        Set.Immutable<V> filter(Predicate<? super V> predicate);

        @Override
        <V2> Set.Immutable<V2> map(Function<? super V, ? extends V2> function);

        @Override
        UnmodifiableIterator<V> iterator();

        @Override
        default Set.Immutable<V> immutable() {
            return this;
        }

    }

    /**
     *
     * @param <V>
     */
    interface Empty<V>
            extends Iteratable.Empty<V>, Set.Immutable<V> {

        @Override
        default Set.Empty<V> filter(Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default Set.Empty<V> tail() {
            return this;
        }

        @Override
        default UnmodifiableIterator<V> iterator() {
            return Iterables.emptyIterator();
        }

        @Override
        @SuppressWarnings("unchecked")
        default <V2> Set.Empty<V2> map(Function<? super V, ? extends V2> function) {
            return (Set.Empty<V2>) this;
        }

        @Override
        default Set.Empty<V> immutable() {
            return this;
        }

    }

}
