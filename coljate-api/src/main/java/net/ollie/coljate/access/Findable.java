package net.ollie.coljate.access;

import java.util.Optional;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

/**
 * An element {@code V} may be found using a {@link Predicate}.
 *
 * @author Ollie
 * @param <V> value type
 */
public interface Findable<V> {

    @Nonnull
    Optional<V> findAny(Predicate<? super V> predicate);

}
