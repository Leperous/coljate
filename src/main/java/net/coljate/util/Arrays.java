package net.coljate.util;

import java.util.Objects;

/**
 *
 * @author ollie
 */
public final class Arrays {

    public static final Object[] EMPTY = new Object[0];

    private Arrays() {
    }

    public static boolean contains(final Object[] array, final Object element) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], element)) {
                return true;
            }
        }
        return false;
    }

    public static Object[] copyOf(final Object[] from) {
        if (from.length == 0) {
            return EMPTY;
        }
        final Object[] to = new Object[from.length];
        System.arraycopy(from, 0, to, 0, from.length);
        return to;
    }

    public static Object[] copyOf(final Object[] from, final int length) {
        return java.util.Arrays.copyOf(from, length);
    }

}
