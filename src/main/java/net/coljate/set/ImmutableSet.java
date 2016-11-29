package net.coljate.set;

import java.util.Collections;

import net.coljate.collection.ImmutableCollection;

/**
 *
 * @author ollie
 */
public interface ImmutableSet<T> extends Set<T>, ImmutableCollection<T> {

    @Override
    @Deprecated
    default ImmutableSet<T> immutableCopy() {
        return this;
    }

    @Override
    @Deprecated
    default java.util.Set<T> javaCollectionCopy() {
        return Collections.unmodifiableSet(Set.super.javaCollectionCopy());
    }

}
