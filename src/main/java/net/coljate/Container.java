package net.coljate;

import net.coljate.collection.Collection;
import net.coljate.feature.complexity.Complexity;
import net.coljate.feature.complexity.TimeComplexity;

/**
 * An object that can "contain" other objects.
 *
 * @author ollie
 * @see Collection
 */
public interface Container {

    /**
     *
     * @param object
     * @return true if this container contains the given object.
     */
    @TimeComplexity(bestCase = Complexity.CONSTANT, worstCase = Complexity.LINEAR)
    boolean contains(Object object);

    /**
     *
     * @return true if there are no objects inside this container.
     */
    @TimeComplexity(Complexity.CONSTANT)
    boolean isEmpty();

}
