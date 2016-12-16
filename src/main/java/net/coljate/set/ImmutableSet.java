package net.coljate.set;

import java.util.Collections;

import net.coljate.collection.Collection;
import net.coljate.collection.ImmutableCollection;
import net.coljate.set.impl.EmptySet;
import net.coljate.set.impl.ImmutableWrappedSet;
import net.coljate.set.lazy.ImmutableLazyUnionSet;
import net.coljate.set.impl.SingletonSet;

/**
 *
 * @author ollie
 */
public interface ImmutableSet<T> extends Set<T>, ImmutableCollection<T> {

    @Override
    default ImmutableSet<T> with(final T element) {
        return this.contains(element)
                ? this
                : ImmutableLazyUnionSet.of(this, Set.of(element));
    }

    @Override
    @Deprecated
    default ImmutableSet<T> immutableCopy() {
        return this;
    }

    @Override
    @Deprecated
    default java.util.Set<T> mutableJavaCopy() {
        return Collections.unmodifiableSet(Set.super.mutableJavaCopy());
    }

    static <T> ImmutableSet<T> of() {
        return EmptySet.instance();
    }

    static <T> ImmutableSet<T> of(final T element) {
        return SingletonSet.of(element);
    }

    static <T> ImmutableSet<T> copyOf(final Collection<? extends T> collection) {
        return ImmutableWrappedSet.copyIntoHashSet(collection);
    }

}
