package net.coljate.list;

import net.coljate.collection.MutableCollection;
import net.coljate.list.impl.MutableNativeArray;
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

    @SafeVarargs
    static <T> MutableList<T> copyOf(final T... elements) {
        return MutableNativeArray.viewOf(elements);
    }

    static <T> SynchronizedList<T> synchronize(final MutableList<T> list) {
        return new SynchronizedList<>(list);
    }

}
