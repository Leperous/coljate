package net.ollie.coljate.lists;

import java.util.RandomAccess;

/**
 *
 * @author Ollie
 * @see List
 */
public class MutableArrayList<T> extends MutableNativeList<T> implements RandomAccess {

    public static <T> MutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new MutableArrayList<>(new java.util.ArrayList<>(collection));
    }

    public static <T> MutableList<T> viewOf(final java.util.ArrayList<T> list) {
        return new MutableArrayList<>(list);
    }

    protected MutableArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
    }

}
