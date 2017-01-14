package net.coljate.set;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;

import net.coljate.collection.Collection;
import net.coljate.list.Array;
import net.coljate.set.impl.ImmutableWrappedSet;
import net.coljate.set.impl.MutableWrappedSet;
import net.coljate.set.impl.UnmodifiableSet;
import net.coljate.set.impl.WrappedSet;
import net.coljate.set.lazy.LazyFilteredSet;
import net.coljate.set.lazy.LazySet;
import net.coljate.set.lazy.LazySetUnion;
import net.coljate.util.Equality;
import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface Set<T> extends Collection<T> {

    @Override
    default MutableSet<T> mutableCopy() {
        return MutableWrappedSet.copyOf(this);
    }

    @Override
    default ImmutableSet<T> immutableCopy() {
        return ImmutableWrappedSet.copyIntoHashSet(this);
    }

    @Override
    default java.util.Set<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.HashSet::new);
    }

    @Override
    @TimeComplexity(computed = true, bestCase = Complexity.LINEAR)
    default SortedSet<T> sortedCopy(final Comparator<? super T> comparator) {
        return SortedSet.copyOf(comparator, this);
    }

    default boolean elementsEqual(final Set<?> that) {
        return that != null && Set.elementsEqual(this, that);
    }

    @Override
    default Set<T> filter(final Predicate<? super T> predicate) {
        return LazyFilteredSet.of(this, predicate);
    }

    /**
     * Intersection.
     *
     * @return a set of elements in both this set and the given set.
     */
    default Set<T> and(final Set<? extends T> that) {
        return LazySet.intersection(this, that);
    }

    /**
     * Relative complement.
     *
     * @return a set of elements in this set but not the given set.
     */
    default Set<T> not(final Set<? extends T> that) {
        return LazySet.complement(this, that);
    }

    /**
     * Union.
     *
     * @return a set of elements in either this set or the given set, or both.
     */
    default Set<T> or(final Set<? extends T> that) {
        return LazySetUnion.of(this, that);
    }

    /**
     * Symmetric difference.
     *
     * @return a set of elements in either this set or the given set, but not both.
     */
    default Set<T> xor(final Set<? extends T> that) {
        return LazySet.difference(this, that);
    }

    @Override
    default Spliterator<T> spliterator() {
        return Spliterators.spliterator(this.iterator(), this.count(), Spliterator.SIZED | Spliterator.DISTINCT);
    }

    @Override
    @Deprecated
    default Set<T> distinct() {
        return this;
    }

    static <T> Set<T> of() {
        return ImmutableSet.of();
    }

    static <T> Set<T> of(final T element) {
        return ImmutableSet.of(element);
    }

    @SafeVarargs
    static <T> Set<T> of(final T... elements) {
        return ImmutableSet.of(elements);
    }

    static <T> Set<T> ofNonNull(final T a, final T b) {
        return ImmutableSet.ofNonNull(a, b);
    }

    static <T> Set<T> unmodifiable(final Set<? extends T> set) {
        return UnmodifiableSet.viewOf(set);
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

    static <T> Set<T> viewOf(final java.util.Set<? extends T> set) {
        return new WrappedSet<>(set);
    }

    static <T> Set<T> ofOptional(final Optional<? extends T> optional) {
        return optional.map(Set::of).orElseGet(Set::of);
    }

    @SafeVarargs
    static <T> Set<T> ofOptionals(final Optional<? extends T>... optionals) {
        return Array.viewOf(optionals)
                .filter(Optional::isPresent)
                .transform((Optional<? extends T> o) -> (T) o.get())
                .distinct();
    }

    @SafeVarargs
    static <T> Set<T> ofNonNull(final T... elements) {
        return of(elements).filter(Objects::nonNull);
    }

    static int elementHash(final Set<?> set) {
        throw new UnsupportedOperationException();
    }

    static boolean elementsEqual(final Set<?> s1, final Set<?> s2) {
        return Equality.unorderedEquals(s1, s2);
    }

}
