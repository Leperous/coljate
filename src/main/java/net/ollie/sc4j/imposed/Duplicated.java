package net.ollie.sc4j.imposed;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.utils.NonNegative;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @see Unique
 */
public interface Duplicated<V>
        extends Collection<V>, Keyed<V, NonNegative> {

    int rawCount(Object value);

    default NonNegative count(final Object value) {
        return NonNegative.of(this.rawCount(value));
    }

    @Override
    @Nonnull
    default NonNegative get(final Object value) {
        return this.count(value);
    }

}
