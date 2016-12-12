package net.coljate.util;

import net.coljate.feature.complexity.Complexity;
import net.coljate.feature.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public class Hash {

    @TimeComplexity(Complexity.LINEAR)
    public static int orderedHash(final Iterable<?> iterable) {
        int hashCode = 1;
        for (final Object element : iterable) {
            hashCode = 31 * hashCode + (element == null ? 0 : element.hashCode());
        }
        return hashCode;
    }

    public static int unorderedHash(final Iterable<?> iterable) {
        throw new UnsupportedOperationException();
    }

}
