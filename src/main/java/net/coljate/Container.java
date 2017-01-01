package net.coljate;

import net.coljate.collection.Collection;
import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.MemoryComplexity;
import net.coljate.util.complexity.TimeComplexity;

/**
 * An object that can "contain" other objects.
 *
 * @author ollie
 * @see Collection
 */
public interface Container {

    /**
     * @param object
     * @return true if this container contains the given object, according to its {@link Object#equals equals} method.
     */
    @TimeComplexity(bestCase = Complexity.CONSTANT, worstCase = Complexity.LINEAR)
    @MemoryComplexity(Complexity.CONSTANT)
    boolean contains(Object object);

    /**
     * @return true if there are no objects inside this container.
     */
    @TimeComplexity(Complexity.CONSTANT)
    @MemoryComplexity(Complexity.CONSTANT)
    boolean isEmpty();

}
