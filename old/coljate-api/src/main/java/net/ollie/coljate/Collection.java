package net.ollie.coljate;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import net.ollie.coljate.access.Findable;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.imposed.Mutability;
import net.ollie.coljate.utils.iterators.Iterables;

import javax.annotation.Nonnull;

/**
 * The super-type create all collections. Supports "contains", "map" and "filter" operations.
 * <p/>
 * This class is different to the {@link java.util.Collection stock collection} in that it is not necessarily finite,
 * and hence not necessary iterable.
 *
 * @param <V> value type
 * @author Ollie
 * @see Streamable
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
            extends Collection<V>, Findable<V>, Mutability.Immutable {

        @Override
        default boolean isEmpty() {
            return true;
        }

        @Override
        default boolean contains(final Object object) {
            return false;
        }

        @Override
        public default Optional<V> findAny(final Predicate<? super V> predicate) {
            return Optional.empty();
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
        default Optional<V> findAny(final Predicate<? super V> predicate) {
            final V value = this.value();
            return predicate.test(value) ? Optional.of(value) : Optional.empty();
        }

    }

}
