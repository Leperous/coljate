package net.coljate.util;

import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

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

    @TimeComplexity(Complexity.LINEAR)
    public static int unorderedHash(final Iterable<?> iterable) {
        int hashCode = 0;
        for (final Object element : iterable) {
            hashCode += (element == null || element == iterable ? 0 : element.hashCode());
        }
        return hashCode;
    }

}
