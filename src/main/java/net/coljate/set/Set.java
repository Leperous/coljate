package net.coljate.set;

import java.util.Comparator;
import java.util.function.Predicate;

import net.coljate.collection.Collection;
import net.coljate.set.impl.EmptySet;
import net.coljate.set.impl.ImmutableWrappedSet;
import net.coljate.set.impl.MutableWrappedHashSet;
import net.coljate.set.impl.MutableWrappedSet;
import net.coljate.set.impl.MutableWrappedTreeSet;
import net.coljate.set.impl.SingletonSet;
import net.coljate.set.impl.WrappedSet;
import net.coljate.set.lazy.LazyFilteredSet;
import net.coljate.set.lazy.LazyIntersectionSet;
import net.coljate.set.lazy.LazySet;
import net.coljate.set.lazy.LazyUnionSet;
import net.coljate.util.Equality;

/**
 *
 * @author ollie
 */
public interface Set<T> extends Collection<T> {

    @Override
    default MutableSet<T> mutableCopy() {
        return MutableWrappedSet.copyOf(this);
    }

    @Override
    default ImmutableSet<T> immutableCopy() {
        return ImmutableWrappedSet.copyOf(this);
    }

    @Override
    default java.util.Set<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.HashSet::new);
    }

    @Override
    default SortedSet<T> sortedCopy(final Comparator<? super T> comparator) {
        return MutableWrappedTreeSet.copyOf(comparator, this);
    }

    default boolean elementsEqual(final Set<?> that) {
        return that != null && Set.elementsEqual(this, that);
    }

    @Override
    default LazySet<T> filter(final Predicate<? super T> predicate) {
        return LazyFilteredSet.of(this, predicate);
    }

    default LazySet<T> intersection(final Set<? extends T> that) {
        return LazyIntersectionSet.of(this, that);
    }

    default LazySet<T> union(final Set<? extends T> that) {
        return LazyUnionSet.of(this, that);
    }

    static <T> EmptySet<T> of() {
        return EmptySet.instance();
    }

    static <T> SingletonSet<T> of(final T element) {
        return SingletonSet.of(element);
    }

    @SafeVarargs
    static <T> Set<T> of(final T... elements) {
        //FIXME use immutable set
        return MutableWrappedHashSet.copyOf(elements);
    }

    static <T> Set<T> copyOrCast(final Collection<T> collection) {
        return collection instanceof Set
                ? (Set<T>) collection
                : copyOf(collection);
    }

    static <T> Set<T> copyOf(final java.util.Collection<? extends T> collection) {
        return MutableWrappedSet.copyIntoHashSet(collection);
    }

    static <T> Set<T> copyOf(final Collection<? extends T> collection) {
        return ImmutableSet.copyOf(collection);
    }

    static <T> Set<T> viewOf(final java.util.Set<T> set) {
        return WrappedSet.viewOf(set);
    }

    static int elementHash(final Set<?> set) {
        throw new UnsupportedOperationException();
    }

    static boolean elementsEqual(final Set<?> s1, final Set<?> s2) {
        return Equality.unorderedEquals(s1, s2);
    }

}
