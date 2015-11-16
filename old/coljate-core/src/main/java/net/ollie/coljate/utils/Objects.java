package net.ollie.coljate.utils;

/**
 *
 * @author Ollie
 */
public final class Objects {

    private Objects() {
    }

    public static <T> T coalesce(final T o1) {
        return o1;
    }

    public static <T> T coalesce(final T o1, final T o2) {
        return o1 == null ? o2 : o1;
    }

    @SafeVarargs
    public static <T> T coalesce(final T... objects) {
        for (final T object : objects) {
            if (object != null) {
                return object;
            }
        }
        return null;
    }

}
