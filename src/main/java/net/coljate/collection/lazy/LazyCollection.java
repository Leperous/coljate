package net.coljate.collection.lazy;

import net.coljate.collection.Collection;

/**
 *
 * @author ollie
 */
public interface LazyCollection<T> extends Collection<T> {

    default Collection<? extends T> evaluate() {
        return this.immutableCopy();
    }

}
