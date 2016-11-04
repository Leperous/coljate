package net.coljate.list;

import java.util.Iterator;
import java.util.RandomAccess;

/**
 *
 * @author ollie
 */
public interface Array<T> extends List<T>, RandomAccess {

    T get(int index);

    @Override
    ArrayIterator<T> iterator();

    @Override
    default MutableArray<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default ImmutableArray<T> immutableCopy() {
        throw new UnsupportedOperationException();
    }


}
