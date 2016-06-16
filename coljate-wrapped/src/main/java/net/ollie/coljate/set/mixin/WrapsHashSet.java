package net.ollie.coljate.set.mixin;

import java.util.Iterator;
import java.util.Set;

import net.ollie.coljate.set.MutableSet;
import net.ollie.coljate.set.MutableWrappedHashSet;

/**
 * Some {@link Set} that wraps a {@link java.util.HashSet}.
 *
 * @author Ollie
 */
public interface WrapsHashSet<T> extends WrapsSet<T> {

    @Override
    java.util.HashSet<T> copyDelegate();

    @Override
    default MutableSet<T> mutableCopy() {
        return MutableWrappedHashSet.viewOf(this.copyDelegate());
    }

    static <T> java.util.HashSet<T> copyIntoHashSet(final java.util.Collection<? extends T> collection) {
        return new java.util.HashSet<>(collection);
    }

    static <T> java.util.HashSet<T> copyIntoHashSet(final Iterable<? extends T> iterable) {
        return copyIntoHashSet(iterable.iterator());
    }

    static <T> java.util.HashSet<T> copyIntoHashSet(final Iterator<? extends T> iterator) {
        final java.util.HashSet<T> hashSet = new java.util.HashSet<>();
        iterator.forEachRemaining(hashSet::add);
        return hashSet;
    }

}
