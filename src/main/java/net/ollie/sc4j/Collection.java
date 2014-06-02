package net.ollie.sc4j;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.imposed.Mutability;
import net.ollie.sc4j.utils.Iterables;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * The super-type of all collections. Supports "contains", "map" and "filter" operations.
 *
 * @author Ollie
 * @param <V> value type
 * @see java.util.Collection
 * @see Iteratable
 */
public interface Collection<V> {

    boolean isEmpty();

    boolean contains(Object object);

    default boolean containsAny(final Iterable<?> iterable) {
        return Iterables.containsAny(this, iterable);
    }

    default boolean containsAll(final Iterable<?> iterable) {
        return Iterables.containsAll(this, iterable);
    }

    @CheckReturnValue
    @Nonnull
    <V2> Collection<V2> map(@Nonnull Function<? super V, ? extends V2> function);

    @CheckReturnValue
    @Nonnull
    Collection<V> filter(@Nonnull Predicate<? super V> predicate);

    default V find(@Nonnull final Predicate<? super V> predicate) throws NoSuchElementException {
        final V found = this.findOrElse(predicate, null);
        if (found == null) {
            throw new NoSuchElementException();
        } else {
            return found;
        }
    }

    @CheckForNull
    V findOrElse(Predicate<? super V> predicate, V defaultValue);

    Collection.Mutable<V> mutableCopy();

    Collection.Immutable<V> immutableCopy();

    interface Mutable<V>
            extends Collection<V>, Mutability.Mutable {

    }

    interface Immutable<V>
            extends Collection<V>, Mutability.Immutable {

        @Override
        default Collection.Immutable<V> immutableCopy() {
            return this;
        }

    }

    interface Empty<V>
            extends Collection.Immutable<V> {

        @Override
        default boolean isEmpty() {
            return true;
        }

        @Override
        default boolean contains(final Object object) {
            return false;
        }

        @Override
        @SuppressWarnings("unchecked")
        default <V2> Collection.Empty<V2> map(Function<? super V, ? extends V2> function) {
            return (Collection.Empty<V2>) this;
        }

        @Override
        default Collection.Empty<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            return defaultValue;
        }

    }

    interface Singleton<V>
            extends Collection.Immutable<V> {

        @Nonnull
        V value();

        @Override
        default boolean isEmpty() {
            return true;
        }

        @Override
        default boolean contains(final Object object) {
            return Objects.equals(this.value(), object);
        }

        @Override
        default V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            return predicate.test(this.value())
                    ? this.value()
                    : defaultValue;
        }

    }

}
