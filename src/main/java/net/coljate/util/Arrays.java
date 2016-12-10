package net.coljate.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public static <T> void consume(final T[] elements, final Consumer<T> consumer) {
        for (int i = 0; i < elements.length; i++) {
            consumer.accept(elements[i]);
        }
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

    public static int indexOf(final Object[] array, final Object element) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Transform all elements in the source array, resizing the given prototype array if necessary.
     *
     * @param <F>
     * @param <T>
     * @param from
     * @param transform
     * @param prototype
     * @return
     */
    public static <F, T> T[] transformAllSource(final F[] from, final Function<? super F, ? extends T> transform, final T[] prototype) {
        @SuppressWarnings("unchecked")
        final T[] into = prototype.length >= from.length
                ? prototype
                : (T[]) java.lang.reflect.Array.newInstance(prototype.getClass().getComponentType(), from.length);
        for (int i = 0; i < from.length; i++) {
            into[i] = transform.apply(from[i]);
        }
        return into;
    }

}
