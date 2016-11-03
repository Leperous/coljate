package net.coljate.utils;

import java.util.Objects;

/**
 *
 * @author ollie
 */
public final class Arrays {

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

}
