package net.coljate.sequence;

import javax.annotation.CheckForNull;

import net.coljate.list.List;
import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

/**
 * Has a specific order following a definite {@link #first first} element.
 *
 * May be {@link List finite} or infinite.
 *
 * @author Ollie
 * @since 1.0
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
