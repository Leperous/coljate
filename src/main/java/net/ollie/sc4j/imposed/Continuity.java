package net.ollie.sc4j.imposed;

import net.ollie.sc4j.access.Iteratable;

/**
 *
 * @author Ollie
 */
public interface Continuity<C extends Continuity<C>> {

    interface DiscreteFinite<T>
            extends Continuity<DiscreteFinite<T>>, Iteratable<T> {

    }

    interface DiscreteInfinite<T>
            extends Continuity<DiscreteInfinite<T>> {

    }

    interface Continuous
            extends Continuity<Continuous> {

    }

}
