package net.ollie.sc4j.imposed;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.utils.NonNegativeInteger;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Distinctness<V, D extends Distinctness<V, D>> {

    interface Unique<V>
            extends Distinctness<V, Unique<V>>, Collection<V> {

    }

    interface Duplicated<V>
            extends Distinctness<V, Duplicated<V>>, Collection<V>, Keyed.Single<V, NonNegativeInteger> {

        int count(Object value);

        @Override
        @Nonnull
        default NonNegativeInteger get(final Object value) {
            return NonNegativeInteger.of(this.count(value));
        }

        @Override
        default boolean isEmpty() {
            return Keyed.Single.super.isEmpty();
        }

        Unique<V> unique();

    }

}
