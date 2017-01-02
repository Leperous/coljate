package net.coljate.list;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.function.Predicate;

import net.coljate.collection.Collection;
import net.coljate.collection.SortedCollection.SortingAlgorithm;
import net.coljate.list.impl.ImmutableSortedArray;
import net.coljate.list.impl.MutableWrappedArrayList;
import net.coljate.list.impl.WrappedList;
import net.coljate.list.lazy.LazyFilteredList;
import net.coljate.list.lazy.LazyTransformedList;
import net.coljate.util.Equality;

/**
 *
 * @author ollie
 * @see java.util.Deque
 * @see Array
 */
public interface List<T> extends Ordered<T>, Collection<T> {

    @Override
    ListIterator<T> iterator();

    @Override
    default T first() {
        return this.iterator().next();
    }

    T last();

    @Override
    default java.util.List<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.ArrayList::new);
    }

    default boolean elementsEqual(final List<?> that) {
        return Equality.orderedEquals(this, that);
    }

    @Override
    default <R> List<R> transform(final Function<? super T, ? extends R> transformation) {
        return new LazyTransformedList<>(this, transformation);
    }

    @Override
    default List<T> filter(final Predicate<? super T> predicate) {
        return new LazyFilteredList<>(this, predicate);
    }

    @Override
    default SortedList<T> sortedCopy(final Comparator<? super T> comparator) {
        return this.sortedCopy(comparator, SortingAlgorithm.DEFAULT);
    }

    @Override
    default SortedList<T> sortedCopy(final Comparator<? super T> comparator, final SortingAlgorithm sortingAlgorithm) {
        return ImmutableSortedArray.sort(this, comparator, sortingAlgorithm);
    }

    @Override
    default MutableList<T> mutableCopy() {
        return MutableWrappedArrayList.viewOf(this.mutableJavaCopy(java.util.ArrayList::new));
    }

    @Override
    default ImmutableList<T> immutableCopy() {
        return ImmutableList.copyOf(this);
    }

    @Override
    default Spliterator<T> spliterator() {
        return Spliterators.spliterator(this.iterator(), this.count(), Spliterator.SIZED | Spliterator.ORDERED);
    }

    @SafeVarargs
    static <T> List<T> copyOf(final T... elements) {
        return ImmutableList.copyOf(elements);
    }

    static <T> List<T> copyOf(final Collection<? extends T> collection) {
        return ImmutableList.copyOf(collection);
    }

    @SuppressWarnings("unchecked")
    static <T> List<T> copyOrCast(final Collection<? extends T> collection) {
        return collection instanceof List
                ? (List<T>) collection
                : copyOf(collection);
    }

    static <T> List<T> viewOf(final java.util.List<? extends T> list) {
        return new WrappedList<>(list);
    }

    static <T> List<T> repeated(final T value, final int count) {
        return Array.repeated(value, count);
    }

}
