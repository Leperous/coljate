package net.coljate.set.lazy;

import java.util.function.Function;
import java.util.function.Predicate;

import net.coljate.collection.Collection;
import net.coljate.collection.lazy.LazyCollection;
import net.coljate.set.ImmutableSet;
import net.coljate.set.MutableSet;
import net.coljate.set.Set;

/**
 *
 * @author Ollie
 */
public interface LazySet<T> extends LazyCollection<T>, Set<T> {

    @Override
    default MutableSet<T> mutableCopy() {
        return Set.super.mutableCopy();
    }

    @Override
    default ImmutableSet<T> immutableCopy() {
        return Set.super.immutableCopy();
    }

    static <T> Function<Collection<T>, ? extends LazySet<T>> filter(final Predicate<? super T> predicate) {
        return collection -> new LazyFilteredSet<>(Set.copyOrCast(collection), predicate);
    }

    static <F, T> Function<Collection<F>, ? extends LazySet<T>> transform(final Function<? super F, ? extends T> transformation) {
        return collection -> new LazyTransformedSet<>(Set.copyOrCast(collection), transformation);
    }

}
