package net.coljate.list;

import net.coljate.collection.Collection;
import net.coljate.collection.MutableCollection;
import net.coljate.list.impl.MutableNativeArray;
import net.coljate.list.impl.MutableWrappedArrayList;
import net.coljate.list.impl.MutableWrappedList;
import net.coljate.list.impl.SynchronizedList;

/**
 *
 * @author ollie
 */
public interface MutableList<T> extends List<T>, MutableCollection<T> {

    void prefix(T element);

    void suffix(T element);

    default SynchronizedList<T> synchronizedCopy() {
        return synchronize(this);
    }

    static <T> MutableList<T> create(final int size) {
        return MutableWrappedArrayList.create(size);
    }

    @SafeVarargs
    static <T> MutableList<T> copyOf(final T... elements) {
        return MutableNativeArray.viewOf(elements);
    }

    static <T> MutableList<T> copyOf(final Collection<? extends T> collection) {
        return MutableArray.copyOf(collection);
    }

    static <T> MutableList<T> viewOf(final java.util.List<T> list) {
        return new MutableWrappedList<>(list);
    }

    static <T> SynchronizedList<T> synchronize(final MutableList<T> list) {
        return new SynchronizedList<>(list);
    }

}
