package net.coljate.util;

/**
 *
 * @author ollie
 */
public class Hash {

    public static int orderedHash(final Iterable<?> iterable) {
        int hashCode = 1;
        for (final Object element : iterable) {
            hashCode = 31 * hashCode + (element == null ? 0 : element.hashCode());
        }
        return hashCode;
    }

}
