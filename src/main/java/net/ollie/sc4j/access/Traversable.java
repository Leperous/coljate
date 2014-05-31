package net.ollie.sc4j.access;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.imposed.Mutability;

/**
 *
 * @author Ollie
 */
public interface Traversable<V>
        extends Collection<V> {

    V head();

    Traversable<V> tail();

    Traversable.Mutable<V> mutableCopy();

    Traversable.Immutable<V> immutableCopy();

    interface Mutable<V>
            extends Traversable<V>, Mutability.Mutable {

    }

    interface Immutable<V>
            extends Traversable<V>, Mutability.Immutable {

        @Override
        default Traversable.Immutable<V> immutableCopy() {
            return this;
        }

    }

}
