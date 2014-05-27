package net.ollie.sc4j;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.sc4j.utils.Iterables;

/**
 *
 * @author Ollie
 * @param <V> value type
 * @see java.util.Collection
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
    <V2> Collection<V2> map(Function<? super V, ? extends V2> function);

    @CheckReturnValue
    @Nonnull
    Collection<V> filter(Predicate<? super V> predicate);

    default V find(Predicate<? super V> predicate) throws NoSuchElementException {
        final V found = this.findOrElse(predicate, null);
        if (found == null) {
            throw new NoSuchElementException();
        } else {
            return found;
        }
    }

    V findOrElse(Predicate<? super V> predicate, V defaultValue);

}
