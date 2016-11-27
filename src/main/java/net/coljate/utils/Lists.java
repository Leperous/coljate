package net.coljate.utils;

/**
 *
 * @author ollie
 */
public class Lists {

    public static <T> java.util.List<T> asUnmodifiableList(final java.util.Collection<T> collection) {
        return java.util.Collections.unmodifiableList(collection instanceof java.util.List
                ? ((java.util.List<T>) collection)
                : new java.util.ArrayList<>(collection));
    }

}
