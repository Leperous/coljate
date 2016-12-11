package net.coljate.list;

import java.util.function.Function;
import java.util.function.Predicate;

import net.coljate.collection.Collection;
import net.coljate.feature.Ordered;
import net.coljate.list.impl.ImmutableNativeArray;
import net.coljate.list.impl.MutableWrappedArrayList;
import net.coljate.list.impl.MutableWrappedList;
import net.coljate.list.lazy.LazyFilteredList;
import net.coljate.list.lazy.LazyList;
import net.coljate.list.lazy.LazyTransformedList;
import net.coljate.util.Equality;

/**
 *
 * @author ollie
 */
public interface List<T> extends Ordered<T> {

    @Override
    ListIterator<T> iterator();

    @Override
    default T first() {
        return this.iterator().next();
    }

    T last();

    @Override
    default MutableList<T> mutableCopy() {
        return MutableWrappedArrayList.viewOf(this.mutableJavaCopy(java.util.ArrayList::new));
    }

    @Override
    default ImmutableList<T> immutableCopy() {
        return ImmutableNativeArray.copyOf(this);
    }

    @Override
    default java.util.List<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.ArrayList::new);
    }

    default boolean elementsEqual(final List<?> that) {
        return Equality.orderedEquals(this, that);
    }

    @Override
    default <R> LazyList<R> transform(final Function<? super T, ? extends R> transformation) {
        return new LazyTransformedList<>(this, transformation);
    }

    @Override
    default LazyList<T> filter(final Predicate<? super T> predicate) {
        return new LazyFilteredList<>(this, predicate);
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

    static <T> MutableWrappedList<T> viewOf(final java.util.List<T> list) {
        return MutableWrappedList.viewOf(list);
    }

    static <T> List<T> repeated(final T value, final int count) {
        return Array.repeated(value, count);
    }

}
