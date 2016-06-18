package net.ollie.coljate.list.mixin;

import net.ollie.coljate.Collection;
import net.ollie.coljate.list.Array;
import net.ollie.coljate.list.ImmutableArray;
import net.ollie.coljate.list.List;
import net.ollie.coljate.list.MutableArray;

/**
 * Some {@link List} that wraps a {@link java.util.ArrayList}.
 *
 * @author Ollie
 */
public interface WrapsArrayList<T>
        extends Array<T>, WrapsList<T>, CopiedToArray<T> {

    @Override
    java.util.ArrayList<T> copyDelegate();

    @Override
    default int capacity() {
        throw new UnsupportedOperationException(); //TODO reflection?
    }

    @Override
    default MutableArray<T> mutableCopy() {
        return CopiedToArray.super.mutableCopy();
    }

    @Override
    default ImmutableArray<T> immutableCopy() {
        return CopiedToArray.super.immutableCopy();
    }

    @SafeVarargs
    static <T> java.util.ArrayList<T> copyToArrayList(final T... array) {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(array.length);
        for (final T element : array) {
            list.add(element);
        }
        return list;
    }

    static <T> java.util.ArrayList<T> copyToArrayList(final java.util.Collection<? extends T> collection) {
        return new java.util.ArrayList<>(collection);
    }

    static <T> java.util.ArrayList<T> copyToArrayList(final Collection<? extends T> collection) {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(collection.count());
        collection.forEach(list::add);
        return list;
    }

}
