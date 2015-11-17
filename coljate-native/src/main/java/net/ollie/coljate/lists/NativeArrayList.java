package net.ollie.coljate.lists;

import java.util.RandomAccess;

import net.ollie.coljate.Collection;

/**
 *
 * @author Ollie
 */
public class NativeArrayList<T> extends NativeList<T> implements RandomAccess {

    public static <T> java.util.ArrayList<T> copyToArrayList(final java.util.Collection<? extends T> collection) {
        return new java.util.ArrayList<>(collection);
    }

    public static <T> java.util.ArrayList<T> copyToArrayList(final Collection<? extends T> collection) {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(collection.size());
        collection.forEach(list::add);
        return list;
    }

    final java.util.ArrayList<T> delegate;

    protected NativeArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    protected java.util.ArrayList<T> copyDelegate() {
        return copyToArrayList(delegate);
    }

}
