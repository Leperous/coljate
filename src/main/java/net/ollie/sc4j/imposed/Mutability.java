package net.ollie.sc4j.imposed;

/**
 *
 * @author Ollie
 */
public interface Mutability<V extends Mutability<V>> {

    interface Mutable
            extends Mutability<Mutable> {

        Immutable immutable();

        void clear();

    }

    @javax.annotation.concurrent.Immutable
    interface Immutable
            extends Mutability<Immutable>, ThreadSafe {

        Mutable mutable();

        default Immutable immutable() {
            return this;
        }

    }

}
