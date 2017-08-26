package net.coljate.sequence;

import javax.annotation.CheckForNull;

import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

/**
 * Has a specific order following a definite {@link #first first} element.
 *
 * May or may not be infinite.
 *
 * @author ollie
 */
public interface Sequence<T> {

    /**
     *
     * @return the first element in this ordered collection, or {@code null} if empty.
     */
    @TimeComplexity(Complexity.CONSTANT)
    @CheckForNull
    T first();

}
