package net.ollie.sc4j.imposed;

import javax.annotation.concurrent.NotThreadSafe;

/**
 *
 * @author Ollie
 */
public interface Mutability<V extends Mutability<V>> {

    Mutable mutableCopy();

    Immutable immutableCopy();

    @NotThreadSafe
    interface Mutable
            extends Mutability<Mutable> {

        void clear();

    }

    @javax.annotation.concurrent.Immutable
    @javax.annotation.concurrent.ThreadSafe
    interface Immutable
            extends Mutability<Immutable> {

        @Override
        default Immutable immutableCopy() {
            return this;
        }

    }

}
