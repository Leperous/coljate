package net.ollie.sc4j.imposed;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.access.Keyed;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @see Unique
 */
public interface Duplicated<V>
        extends Collection<V>, Keyed<V, Integer> {

    int count(V value);

    @Override
    @Nonnull
    default Integer get(final V value) {
        return this.count(value);
    }

}
