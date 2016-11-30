package net.coljate.list;

import net.coljate.collection.MutableCollection;
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

    static <T> SynchronizedList<T> synchronize(final MutableList<T> list) {
        return new SynchronizedList<>(list);
    }

}
