package net.ollie.sc4j.imposed;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.sc4j.Collection;

/**
 * Marker interface to indicate that a value {@code V} can only exist within
 * this collection zero or one times.
 *
 * @author Ollie
 * @see Duplicated
 */
public interface Unique<V>
        extends Collection<V> {

    @CheckReturnValue
    @Nonnull
    Unique.Mutable<V> mutable();

    @CheckReturnValue
    @Nonnull
    Unique.Immutable<V> immutable();

    interface Mutable<V>
            extends Unique<V>, Mutability.Mutable {

    }

    interface Immutable<V>
            extends Unique<V>, Mutability.Immutable {

        @Override
        default Unique.Immutable<V> immutable() {
            return this;
        }

    }

}
