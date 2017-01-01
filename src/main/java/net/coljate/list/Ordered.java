package net.coljate.list;

import javax.annotation.CheckForNull;

import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

/**
 * Has a specific order.
 *
 * @author ollie
 */
public interface Ordered<T> {

    /**
     *
     * @return the first element in this ordered collection, or {@code null} if empty.
     */
    @TimeComplexity(Complexity.CONSTANT)
    @CheckForNull
    T first();

}
