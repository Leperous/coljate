package net.ollie.sc4j.imposed;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.utils.NonNegativeInteger;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @see Unique
 */
public interface Duplicated<V>
        extends Collection<V>, Keyed<V, NonNegativeInteger> {

    int count(Object value);

    @Override
    @Nonnull
    default NonNegativeInteger get(final Object value) {
        return NonNegativeInteger.of(this.count(value));
    }

}
