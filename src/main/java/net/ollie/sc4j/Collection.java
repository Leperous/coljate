package net.ollie.sc4j;

import net.ollie.sc4j.access.Finite;
import net.ollie.sc4j.utils.Iterables;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

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

    @CheckReturnValue
    @Nonnull
    <V2> Collection<V2> map(@Nonnull Function<? super V, ? extends V2> function);

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

    interface Empty<V>
            extends Collection<V> {

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
        default V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            return defaultValue;
        }

    }

    interface Singleton<V>
            extends Collection<V> {

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
