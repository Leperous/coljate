package net.coljate.set;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.coljate.sequence.Sequence;

/**
 *
 * @author ollie
 */
public interface SequentialSet<T> extends Sequence<T>, Set<T> {

    @Override
    default T first() {
        return Set.super.first();
    }

    @Nonnull
    default Optional<SortedSet<T>> toSortedSet() {
        return this instanceof SortedSet
                ? Optional.of((SortedSet<T>) this)
                : Optional.empty();
    }

}