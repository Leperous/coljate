package net.coljate.list.lazy;

import java.util.function.Function;
import java.util.function.Predicate;

import net.coljate.collection.Collection;
import net.coljate.collection.lazy.LazyCollection;
import net.coljate.list.ImmutableList;
import net.coljate.list.List;
import net.coljate.list.MutableList;

/**
 *
 * @author Ollie
 */
public interface LazyList<T> extends LazyCollection<T>, List<T> {

    @Override
    default ImmutableList<T> immutableCopy() {
        return List.super.immutableCopy();
    }

    @Override
    default MutableList<T> mutableCopy() {
        return List.super.mutableCopy();
    }

    static <T> Function<Collection<T>, ? extends LazyList<T>> filter(final Predicate<? super T> predicate) {
        return collection -> new LazyFilteredList<>(List.copyOrCast(collection), predicate);
    }

    static <F, T> Function<Collection<F>, ? extends LazyList<T>> transform(final Function<? super F, ? extends T> transformation) {
        return (final Collection<F> collection) -> {
            final List<F> list = List.copyOrCast(collection);
            return new LazyTransformedList<>(list, transformation);
        };
    }

}
