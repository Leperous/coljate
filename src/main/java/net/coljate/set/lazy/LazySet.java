package net.coljate.set.lazy;

import net.coljate.collection.lazy.LazyCollection;
import net.coljate.set.Set;

/**
 *
 * @author Ollie
 */
public interface LazySet<T> extends LazyCollection<T>, Set<T> {

    @Override
    default Set<T> evaluate() {
        return this.immutableCopy();
    }

}
