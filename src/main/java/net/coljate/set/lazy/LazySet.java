package net.coljate.set.lazy;

import net.coljate.collection.lazy.LazyCollection;
import net.coljate.set.ImmutableSet;
import net.coljate.set.MutableSet;
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

    @Override
    default MutableSet<T> mutableCopy() {
        return Set.super.mutableCopy();
    }

    @Override
    default ImmutableSet<T> immutableCopy() {
        return Set.super.immutableCopy();
    }

}
