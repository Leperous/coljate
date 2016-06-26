package net.ollie.coljate.set.mixin;

import java.util.Iterator;
import java.util.Set;

import net.ollie.coljate.set.HashSet;
import net.ollie.coljate.set.ImmutableSet;
import net.ollie.coljate.set.MutableSet;

/**
 * Some {@link Set} that wraps a {@link java.util.HashSet}.
 *
 * @author Ollie
 */
public interface WrapsHashSet<T> extends WrapsSet<T>, HashSet<T>, CopiedToHashSet<T> {

    @Override
    java.util.HashSet<T> copyDelegate();

    @Override
    default MutableSet<T> mutableCopy() {
        return CopiedToHashSet.super.mutableCopy();
    }

    @Override
    default ImmutableSet<T> immutableCopy() {
        return CopiedToHashSet.super.immutableCopy();
    }

    @SafeVarargs
    static <T> java.util.HashSet<T> copyIntoHashSet(final T... array) {
        final java.util.HashSet<T> set = new java.util.HashSet<>(array.length);
        for (final T element : array) {
            set.add(element);
        }
        return set;
    }

    static <T> java.util.HashSet<T> copyIntoHashSet(final java.util.Collection<? extends T> collection) {
        return new java.util.HashSet<>(collection);
    }

    static <T> java.util.HashSet<T> copyIntoHashSet(final Iterable<? extends T> iterable) {
        return copyIntoHashSet(iterable.iterator());
    }

    static <T> java.util.HashSet<T> copyIntoHashSet(final Iterator<? extends T> iterator) {
        final java.util.HashSet<T> set = new java.util.HashSet<>();
        iterator.forEachRemaining(set::add);
        return set;
    }

}
