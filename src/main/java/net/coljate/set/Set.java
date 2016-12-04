package net.coljate.set;

import net.coljate.collection.Collection;
import net.coljate.set.impl.LazyUnionSet;
import net.coljate.set.impl.MutableWrappedHashSet;
import net.coljate.set.impl.MutableWrappedSet;
import net.coljate.set.impl.SingletonSet;
import net.coljate.set.impl.WrappedSet;

/**
 *
 * @author ollie
 */
public interface Set<T> extends Collection<T> {

    default Set<T> union(final Set<? extends T> that) {
        return LazyUnionSet.viewOf(this, that);
    }

    @Override
    default MutableSet<T> mutableCopy() {
        return MutableWrappedSet.copyOf(this);
    }

    @Override
    default ImmutableSet<T> immutableCopy() {
        throw new UnsupportedOperationException();
    }

    @Override
    default java.util.Set<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.HashSet::new);
    }

    static <T> Set<T> of(final T element) {
        return new SingletonSet<>(element);
    }

    @SafeVarargs
    static <T> Set<T> copyOf(final T... elements) {
        return MutableWrappedHashSet.copyOf(elements);
    }

    static <T> Set<T> viewOf(final java.util.Set<T> set) {
        return WrappedSet.viewOf(set);
    }

}
