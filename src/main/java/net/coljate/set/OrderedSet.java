package net.coljate.set;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.coljate.list.Ordered;

/**
 *
 * @author ollie
 */
public interface OrderedSet<T> extends Ordered<T>, Set<T> {

    @Nonnull
    default Optional<SortedSet<T>> toSortedSet() {
        return this instanceof SortedSet
                ? Optional.of((SortedSet<T>) this)
                : Optional.empty();
    }

}
