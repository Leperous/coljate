package net.ollie.coljate.imposed;

import net.ollie.coljate.Collection;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

/**
 * Values are duplicated or are distinct.
 *
 * @author Ollie
 */
public interface Distinctness<V, D extends Distinctness<V, D>> {

    NonNegativeInteger count(Object object);

    interface Unique<V>
            extends Distinctness<V, Unique<V>>, Collection<V> {

        @Override
        default NonNegativeInteger count(final Object object) {
            return this.contains(object)
                    ? NonNegativeInteger.ONE
                    : NonNegativeInteger.ZERO;
        }

    }

    interface Duplicated<V>
            extends Distinctness<V, Duplicated<V>>, Collection<V> {

        Unique<V> unique();

    }

}
