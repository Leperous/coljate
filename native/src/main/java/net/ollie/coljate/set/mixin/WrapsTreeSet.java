package net.ollie.coljate.set.mixin;

import java.util.Comparator;
import static java.util.Objects.requireNonNull;
import java.util.TreeSet;
import java.util.function.Supplier;

import net.ollie.coljate.set.ImmutableSortedSet;
import net.ollie.coljate.set.MutableSortedSet;
import net.ollie.coljate.set.MutableWrappedTreeSet;
import net.ollie.coljate.set.Set;
import net.ollie.coljate.set.SortedSet;
import net.ollie.coljate.theory.Finite;

/**
 * Some {@link Set} that wraps a {@link java.util.TreeSet}.
 *
 * @author Ollie
 */
public interface WrapsTreeSet<T> extends WrapsSet<T>, SortedSet<T>, CopiedToTreeSet<T> {

    @Override
    java.util.TreeSet<T> copyDelegate();

    @Override
    default ImmutableSortedSet<T> immutableCopy() {
        return CopiedToTreeSet.super.immutableCopy();
    }

    @Override
    default MutableSortedSet<T> mutableCopy() {
        return MutableWrappedTreeSet.viewOf(this.copyDelegate());
    }

    static <T> java.util.TreeSet<T> copyIntoTreeSet(final Finite<? extends T> collection, final Comparator<? super T> comparator) {
        final java.util.TreeSet<T> treeSet = new java.util.TreeSet<>(comparator);
        collection.forEach(treeSet::add);
        return treeSet;
    }

    static <T> java.util.TreeSet<T> viewAsTreeSet(final java.util.Collection<T> collection, final Supplier<Comparator<? super T>> comparator) {
        return collection instanceof java.util.TreeSet
                ? (java.util.TreeSet<T>) collection
                : copyIntoTreeSet(collection, comparator.get());
    }

    static <T> java.util.TreeSet<T> copyIntoTreeSet(final java.util.Collection<? extends T> collection, final Comparator<? super T> comparator) {
        requireNonNull(comparator);
        final TreeSet<T> set = new java.util.TreeSet<>(comparator);
        set.addAll(collection);
        return set;
    }

}
