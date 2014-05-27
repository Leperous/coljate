package net.ollie.sc4j.imposed;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.Collection;

/**
 *
 * @author Ollie
 */
public interface Empty<V>
        extends Collection<V> {

    @Override
    default boolean contains(final Object object) {
        return false;
    }

    @Override
    default boolean isEmpty() {
        return true;
    }

    @Override
    default Empty<V> filter(final Predicate<? super V> predicate) {
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    default <V2> Empty<V2> map(final Function<? super V, ? extends V2> function) {
        return (Empty<V2>) this;
    }

    @Override
    default V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
        return defaultValue;
    }

}
