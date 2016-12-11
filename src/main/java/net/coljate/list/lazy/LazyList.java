package net.coljate.list.lazy;

import net.coljate.collection.lazy.LazyCollection;
import net.coljate.list.ImmutableList;
import net.coljate.list.List;
import net.coljate.list.MutableList;

/**
 *
 * @author Ollie
 */
public interface LazyList<T> extends LazyCollection<T>, List<T> {

    @Override
    default List<T> evaluate() {
        return this.immutableCopy();
    }

    @Override
    default ImmutableList<T> immutableCopy() {
        return List.super.immutableCopy();
    }

    @Override
    default MutableList<T> mutableCopy() {
        return List.super.mutableCopy();
    }

}
