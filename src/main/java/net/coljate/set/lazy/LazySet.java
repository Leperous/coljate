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

    static <T> LazySet<T> filter(
            final Collection<T> collection,
            final Predicate<? super T> predicate) {
        return new LazyFilteredSet<>(Set.copyOrCast(collection), predicate);
    }

    static <F, T> LazySet<T> transform(
            final Collection<F> collection,
            final Function<? super F, ? extends T> transformation) {
        return new LazyTransformedSet<>(Set.copyOrCast(collection), transformation);
    }

}
