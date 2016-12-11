package net.coljate.list.lazy;

import net.coljate.collection.lazy.LazyCollection;
import net.coljate.list.List;

/**
 *
 * @author Ollie
 */
public interface LazyList<T> extends LazyCollection<T>, List<T> {

    @Override
    default List<T> evaluate() {
        return this.immutableCopy();
    }

}
