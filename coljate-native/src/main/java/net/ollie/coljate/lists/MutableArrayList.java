package net.ollie.coljate.lists;

import net.ollie.coljate.lists.mixin.MutableNativeListMixin;

import java.util.List;

import net.ollie.coljate.Collection;

/**
 *
 * @author Ollie
 * @see List
 */
public class MutableArrayList<T> extends NativeArrayList<T> implements MutableNativeListMixin<T> {

    public static <T> MutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new MutableArrayList<>(copyToArrayList(collection));
    }

    public static <T> MutableList<T> copyOf(final Collection<? extends T> collection) {
        return new MutableArrayList<>(copyToArrayList(collection));
    }

    public static <T> MutableList<T> viewOf(final java.util.ArrayList<T> list) {
        return new MutableArrayList<>(list);
    }

    protected MutableArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
    }

    @Override
    public java.util.List<T> delegate() {
        return delegate;
    }

    @Override
    public MutableList<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
