package net.coljate.list;

import net.coljate.collection.ConcurrentCollection;
import net.coljate.list.impl.SynchronizedList;

/**
 *
 * @author ollie
 */
public interface ConcurrentList<T> extends MutableList<T>, ConcurrentCollection<T> {

    @Override
    ConcurrentList<T> mutableCopy();

    @Override
    @Deprecated
    default SynchronizedList<T> synchronizedCopy() {
        return MutableList.super.synchronizedCopy();
    }

}
