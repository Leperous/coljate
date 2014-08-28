package net.ollie.sc4j.access;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

/**
 * An element {@code V} may be found using a {@link Predicate}.
 *
 * @author Ollie
 * @param <V> value type
 */
public interface Findable<V> {

    default V findFirst(@Nonnull final Predicate<? super V> predicate) throws NoSuchElementException {
        final V found = this.findOrElse(predicate, null);
        if (found == null) {
            throw new NoSuchElementException();
        } else {
            return found;
        }
    }

    V findOrElse(Predicate<? super V> predicate, V defaultValue);

}
