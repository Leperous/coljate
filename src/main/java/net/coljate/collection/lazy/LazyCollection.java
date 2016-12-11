package net.coljate.collection.lazy;

import java.util.function.Function;
import java.util.function.Predicate;

import net.coljate.collection.Collection;
import net.coljate.collection.ImmutableCollection;
import net.coljate.collection.MutableCollection;

/**
 *
 * @author ollie
 */
public interface LazyCollection<T> extends Collection<T> {

    @Override
    default MutableCollection<? extends T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    default ImmutableCollection<? extends T> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    static <T> Function<Collection<T>, ? extends LazyCollection<T>> filter(final Predicate<? super T> predicate) {
        return collection -> new LazyFilteredCollection<>(collection, predicate);
    }

    static <F, T> Function<Collection<F>, ? extends LazyCollection<T>> transform(final Function<? super F, ? extends T> transform) {
        return (collection) -> new LazyTransformedCollection<>(collection, transform);
    }

}
