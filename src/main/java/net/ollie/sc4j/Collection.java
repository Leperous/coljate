package net.ollie.sc4j;

import java.util.Objects;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Findable;
import net.ollie.sc4j.access.Finite;
import net.ollie.sc4j.imposed.Mutability;
import net.ollie.sc4j.utils.Iterables;

import javax.annotation.Nonnull;

/**
 * The super-type create all collections. Supports "contains", "map" and "filter" operations.
 * <p/>
 * This class is different to the {@link java.util.Collection stock collection} in that it is not necessarily finite,
 * and hence not necessary iterable.
 *
 * @param <V> value type
 * @author Ollie
 * @see Finite
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

    @javax.annotation.concurrent.Immutable
    interface Empty<V>
            extends Collection<V>, Mutability.Immutable {

        @Override
        default boolean isEmpty() {
            return true;
        }

        @Override
        default boolean contains(final Object object) {
            return false;
        }

    }

    interface Singleton<V>
            extends Collection<V>, Findable<V> {

        @Nonnull
        V value();

        @Override
        default boolean isEmpty() {
            return false;
        }

        @Override
        default boolean contains(final Object object) {
            return Objects.equals(this.value(), object);
        }

        @Override
        default V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            final V value = this.value();
            return predicate.test(value)
                    ? value
                    : defaultValue;
        }

    }

}
